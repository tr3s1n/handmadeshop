package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.AuthRequestDTO;
import com.tresin.cvproj.handmade_shop.dto.RefreshTokenRequestDTO;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Auth", description = "The Auth API provides endpoints for user authentication, token generation, token refreshing, and logout functionality")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

	@Operation(summary = "Authenticate and Get Token", description = "Authenticates the user with the provided credentials and generates an access token.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Authentication successful"),
			@ApiResponse(responseCode = "401", description = "Invalid credentials")
	})
	@PostMapping("/login")
	ResponseEntity<?> authenticateAndGetToken(@Valid @RequestBody AuthRequestDTO authRequestDTO);

	@Operation(summary = "Refresh Token", description = "Refreshes the access token using the provided refresh token.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
			@ApiResponse(responseCode = "401", description = "Invalid refresh token")
	})
	@PostMapping("/refreshToken")
	ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO);

	@Operation(summary = "Logout", description = "Logs out the user and invalidates the access token.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Logout successful"),
			@ApiResponse(responseCode = "400", description = "Invalid request")
	})
	@PostMapping("/logout")
	ResponseEntity<?> logout(HttpServletRequest request);

	@Operation(summary = "Admin Ping Test", description = "Tests the admin endpoint.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ping successful"),
			@ApiResponse(responseCode = "403", description = "Forbidden")
	})
	@GetMapping("/ping")
	String adminPingTest();
}
