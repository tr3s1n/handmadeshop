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

@Service
public class RefreshTokenService {

	private static final Logger logger = LoggerFactory.getLogger(RefreshTokenService.class);

	@Autowired
	private RefreshTokenRepository refreshTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public RefreshToken createRefreshToken(String username) {
		RefreshToken refreshToken = RefreshToken.builder()
				.user(userRepository.findByUsername(username))
				.token(UUID.randomUUID().toString())
				// TODO: Add expiration time to application.properties file
				.expiryDate(Instant.now().plusMillis(600000))
				.build();
		return refreshTokenRepository.save(refreshToken);
	}

	public Optional<RefreshToken> findByToken(String token) {
		return refreshTokenRepository.findByToken(token);
	}

	public RefreshToken verifyExpiration(RefreshToken token) {
		if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
			refreshTokenRepository.delete(token);
			throw new RuntimeException(token.getToken() + " Refresh token is expired. Please login again!");
		}
		return token;
	}
}