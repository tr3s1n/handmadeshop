package com.tresin.cvproj.handmade_shop.security;

import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private static final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.debug("Entering loadUserByUsername method...");
		User user = userRepository.findByUsername(username);
		if (user == null) {
			logger.error("Username not found: " + username);
			throw new UsernameNotFoundException("Could not found user " + username);
		}
		logger.info("User {} authenticated successfully.", username);
		return new CustomUserDetails(user);
	}
}