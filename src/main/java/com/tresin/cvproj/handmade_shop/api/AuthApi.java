package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.AuthRequestDTO;
import com.tresin.cvproj.handmade_shop.dto.RefreshTokenRequestDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * The Auth API provides endpoints for user authentication, token generation, token refreshing, and logout functionality.
 */
@Tag(name = "Auth", description = "The Auth API provides endpoints for user authentication, token generation, token refreshing, and logout functionality")
@RequestMapping("/api/v1/auth")
public interface AuthApi {

	/**
	 * Authenticates the user with the provided credentials and generates an access token.
	 *
	 * @param authRequestDTO The authentication request containing user credentials.
	 * @return ResponseEntity<?> A response entity containing the generated access token.
	 */
	@Operation(summary = "Authenticate and Get Token", description = "Authenticates the user with the provided credentials and generates an access token.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Authentication successful"),
			@ApiResponse(responseCode = "401", description = "Invalid credentials"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/login")
	ResponseEntity<?> authenticateAndGetToken(@Valid @RequestBody AuthRequestDTO authRequestDTO);

	/**
	 * Refreshes the access token using the provided refresh token.
	 *
	 * @param refreshTokenRequestDTO The refresh token request containing the refresh token.
	 * @return ResponseEntity<?> A response entity containing the refreshed access token.
	 */
	@Operation(summary = "Refresh Token", description = "Refreshes the access token using the provided refresh token.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Token refreshed successfully"),
			@ApiResponse(responseCode = "401", description = "Invalid refresh token"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/refreshToken")
	ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequestDTO refreshTokenRequestDTO);

	/**
	 * Logs out the user and invalidates the access token.
	 *
	 * @param request The HTTP servlet request.
	 * @return ResponseEntity<?> A response entity indicating the success of the logout operation.
	 */
	@Operation(summary = "Logout", description = "Logs out the user and invalidates the access token.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Logout successful"),
			@ApiResponse(responseCode = "400", description = "Invalid request"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/logout")
	ResponseEntity<?> logout(HttpServletRequest request);

	/**
	 * Tests the admin endpoint.
	 *
	 * @return String A message indicating the success of the ping test.
	 */
	@Operation(summary = "Admin Ping Test", description = "Tests the admin endpoint.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Ping successful"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/ping")
	String adminPingTest();
}
