package com.tresin.cvproj.handmade_shop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class RefreshTokenNotFoundException extends AuthenticationException {
	public RefreshTokenNotFoundException(String message) {
		super(message);
	}
}