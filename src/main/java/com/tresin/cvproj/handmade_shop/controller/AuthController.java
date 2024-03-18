package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.AuthApi;
import com.tresin.cvproj.handmade_shop.dto.AuthRequestDTO;
import com.tresin.cvproj.handmade_shop.dto.JwtResponseDTO;
import com.tresin.cvproj.handmade_shop.dto.RefreshTokenRequestDTO;
import com.tresin.cvproj.handmade_shop.exception.RefreshTokenNotFoundException;
import com.tresin.cvproj.handmade_shop.model.RefreshToken;
import com.tresin.cvproj.handmade_shop.security.JwtService;
import com.tresin.cvproj.handmade_shop.security.TokenBlacklist;
import com.tresin.cvproj.handmade_shop.service.RefreshTokenService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController implements AuthApi {

	private final JwtService jwtService;
	private final RefreshTokenService refreshTokenService;
	private final AuthenticationManager authenticationManager;
	private final TokenBlacklist tokenBlacklist;

	public AuthController(JwtService jwtService, RefreshTokenService refreshTokenService, AuthenticationManager authenticationManager, TokenBlacklist tokenBlacklist) {
		this.jwtService = jwtService;
		this.refreshTokenService = refreshTokenService;
		this.authenticationManager = authenticationManager;
		this.tokenBlacklist = tokenBlacklist;
	}

	@Override
	@PostMapping("/login")
	public ResponseEntity<?> authenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequestDTO.getUsername(), authRequestDTO.getPassword()));
			if (authentication.isAuthenticated()) {
				String accessToken = jwtService.GenerateToken(authRequestDTO.getUsername());
				RefreshToken refreshToken = refreshTokenService.createRefreshToken(authRequestDTO.getUsername());

				return ResponseEntity.ok(JwtResponseDTO.builder()
						.accessToken(accessToken)
						.token(refreshToken.getToken())
						.build());
			} else {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
			}
		} catch (BadCredentialsException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password.");
		} catch (UsernameNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during login process.");
		}
	}

	@Override
	@PostMapping("/refreshToken")
	public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO) {
		try {
			JwtResponseDTO jwtResponseDTO = refreshTokenService.findByToken(refreshTokenRequestDTO.getToken())
					.map(refreshTokenService::verifyExpiration)
					.map(RefreshToken::getUser)
					.map(user -> {
						String accessToken = jwtService.GenerateToken(user.getUsername());
						return JwtResponseDTO.builder()
								.accessToken(accessToken)
								.token(refreshTokenRequestDTO.getToken()).build();
					}).orElseThrow(() -> new RefreshTokenNotFoundException("Refresh token is not in the database."));
			return ResponseEntity.ok(jwtResponseDTO);
		} catch (RefreshTokenNotFoundException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid refresh token.");
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while processing the refresh token.");
		}
	}

	@Override
	@PostMapping("/logout")
	public ResponseEntity<?> logout(HttpServletRequest request) {
		try {
			String authorizationHeader = request.getHeader("Authorization");
			if (StringUtils.hasText(authorizationHeader) && authorizationHeader.startsWith("Bearer ")) {
				String token = authorizationHeader.substring(7);
				tokenBlacklist.addToBlacklist(token);
				return ResponseEntity.ok("Logged out successfully.");
			} else {
				return ResponseEntity.badRequest().body("Authorization header with Bearer token is required.");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred during logout.");
		}
	}

	@Override
	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ping")
	public String adminPingTest() {
		try {
			return "Welcome";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
