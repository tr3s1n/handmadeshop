package com.tresin.cvproj.handmade_shop.security;

import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


// TODO: Use Redis instead of storing the blacklist in memory
@Service
public class InMemoryTokenBlacklist implements TokenBlacklist {
	private final Set<String> blacklist = new HashSet<>();

	@Override
	public void addToBlacklist(String token) {
		blacklist.add(token);
	}

	@Override
	public boolean isBlacklisted(String token) {
		return blacklist.contains(token);
	}
}