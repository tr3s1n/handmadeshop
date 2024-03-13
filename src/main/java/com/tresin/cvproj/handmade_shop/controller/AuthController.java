package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.AuthRequestDTO;
import com.tresin.cvproj.handmade_shop.dto.JwtResponseDTO;
import com.tresin.cvproj.handmade_shop.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

	private final JwtService jwtService;
	private final AuthenticationManager authenticationManager;

	public AuthController(JwtService jwtService, AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.authenticationManager = authenticationManager;
	}

	@PostMapping("/login")
	public JwtResponseDTO AuthenticateAndGetToken(@RequestBody AuthRequestDTO authRequestDTO) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						authRequestDTO.getUsername(),
						authRequestDTO.getPassword()));
		if (authentication.isAuthenticated()) {
			return JwtResponseDTO.builder().accessToken(jwtService.GenerateToken(authRequestDTO.getUsername())).build();
		} else {
			throw new UsernameNotFoundException("Invalid user request.");
		}
	}

	@PreAuthorize("hasAuthority('ADMIN')")
	@GetMapping("/ping")
	public String test() {
		try {
			return "Welcome";
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
