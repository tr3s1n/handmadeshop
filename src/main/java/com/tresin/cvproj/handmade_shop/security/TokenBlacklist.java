package com.tresin.cvproj.handmade_shop.security;

public interface TokenBlacklist {
	void addToBlacklist(String token);
	boolean isBlacklisted(String token);
}
