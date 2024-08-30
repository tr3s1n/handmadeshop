package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.RefreshToken;
import com.tresin.cvproj.handmade_shop.repository.RefreshTokenRepository;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

/**
 * The RefreshTokenService class provides operations for managing refresh tokens in the system.
 * It encapsulates the business logic for creating, retrieving, and verifying refresh tokens.
 */
@Service
public class RefreshTokenService {

	private static final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * Creates a new refresh token for a given username.
	 *
	 * @param username The username for which to create a refresh token.
	 * @return The newly created refresh token.
	 * @throws RuntimeException If the user with the provided username is not found.
	 */
	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = RefreshToken.builder()
				.user(userRepository.findByUsername(username))
				.token(UUID.randomUUID().toString())
				// TODO: Add expiration time to application.properties file
				.expiryDate(Instant.now().plusMillis(600000))
				.build();

		if (refreshToken.getUser().isEmpty()) {
			throw new RuntimeException("User with username " + username + " not found!");
		}

		return refreshTokenRepository.save(refreshToken);
	}

	/**
	 * Retrieves a refresh token by its token string.
	 *
	 * @param token The token string.
	 * @return An Optional containing the refresh token, if found.
	 */
	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	/**
	 * Verifies if a refresh token is still valid by checking its expiration date.
	 *
	 * @param token The refresh token to verify.
	 * @return The verified refresh token, or throws an exception if expired.
	 * @throws RuntimeException If the refresh token is expired.
	 */
	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + " Refresh token is expired. Please login again!");
		}
		return token;
	}
}