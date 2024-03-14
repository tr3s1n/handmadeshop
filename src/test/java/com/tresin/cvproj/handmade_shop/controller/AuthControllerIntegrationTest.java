package com.tresin.cvproj.handmade_shop.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Test
	void testAuthenticateAndGetToken() throws Exception {
		// Seeded user with admin role
		String validUsername = "test_admin";
		String validPassword = "adminpassword123";

		String invalidUsername = "baduser";
		String invalidPassword = "badpassword456";

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\": \"" + validUsername + "\", \"password\": \"" + validPassword + "\"}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.accessToken").exists());

		mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/auth/login")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"username\": \"" + invalidUsername + "\", \"password\": \"" + invalidPassword + "\"}"))
				.andExpect(status().isInternalServerError());
	}
}