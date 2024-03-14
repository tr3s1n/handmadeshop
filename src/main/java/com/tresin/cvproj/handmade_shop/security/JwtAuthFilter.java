package com.tresin.cvproj.handmade_shop.security;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private final JwtService jwtService;
	private final UserDetailsServiceImpl userDetailsServiceImpl;
	private final TokenBlacklist tokenBlacklist;
	private final AuthenticationManager authenticationManager;

	public JwtAuthFilter(JwtService jwtService, UserDetailsServiceImpl userDetailsServiceImpl, TokenBlacklist tokenBlacklist, AuthenticationManager authenticationManager) {
		this.jwtService = jwtService;
		this.userDetailsServiceImpl = userDetailsServiceImpl;
		this.tokenBlacklist = tokenBlacklist;
		this.authenticationManager = authenticationManager;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		String authHeader = request.getHeader("Authorization");
		String token = null;
		String username = null;

		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			token = authHeader.substring(7);
			username = jwtService.extractUsername(token);
		}

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(username);
			if (jwtService.validateToken(token, userDetails) && !tokenBlacklist.isBlacklisted(token)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				Authentication authentication = authenticationManager.authenticate(authenticationToken);
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		filterChain.doFilter(request, response);
	}
}