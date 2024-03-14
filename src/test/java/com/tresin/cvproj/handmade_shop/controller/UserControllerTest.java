package com.tresin.cvproj.handmade_shop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tresin.cvproj.handmade_shop.dto.UserDTO;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.Collections;
import java.util.Optional;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
@TestPropertySource(locations = "classpath:test.properties")
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Value("${test.username}")
	private String testUsername;

	@Value("${test.password}")
	private String testPassword;

	@Test
	void testCreateUser() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("testUser");
		userDTO.setEmail("test@example.com");

		User createdUser = new User();
		createdUser.setId(1L);
		createdUser.setUsername("testUser");
		createdUser.setEmail("test@example.com");

		when(userService.createUser(any(User.class))).thenReturn(createdUser);

		ResultActions resultActions = mockMvc.perform(post("/api/v1/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDTO))
				.with(httpBasic(testUsername, testPassword))
				.with(csrf()));

		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.username", is("testUser")))
				.andExpect(jsonPath("$.email", is("test@example.com")));
	}

	@Test
	void testUpdateUser() throws Exception {
		UserDTO userDTO = new UserDTO();
		userDTO.setUsername("updatedUser");
		userDTO.setEmail("updated@example.com");

		User updatedUser = new User();
		updatedUser.setId(1L);
		updatedUser.setUsername("updatedUser");
		updatedUser.setEmail("updated@example.com");

		when(userService.updateUser(eq(1L), any(User.class))).thenReturn(updatedUser);
		when(userService.updateUser(eq(2L), any(User.class))).thenReturn(null);

		ResultActions resultActions = mockMvc.perform(put("/api/v1/users/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(userDTO))
				.with(httpBasic(testUsername, testPassword))
				.with(csrf()));

		resultActions.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.username", is("updatedUser")))
				.andExpect(jsonPath("$.email", is("updated@example.com")));

		// Test not found scenario
		mockMvc.perform(put("/api/users/2")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(userDTO))
						.with(httpBasic(testUsername, testPassword))
						.with(csrf()))
				.andExpect(status().isNotFound());
	}

	@Test
	void testDeleteUser() throws Exception {
		mockMvc.perform(delete("/api/v1/users/1")
						.with(httpBasic(testUsername, testPassword))
						.with(csrf()))
				.andExpect(status().isNoContent());

		verify(userService, times(1)).deleteUser(1L);
	}

	@Test
	void testGetAllUsers() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setUsername("testUser");
		user.setEmail("test@example.com");

		when(userService.getAllUsers()).thenReturn(Collections.singletonList(user));

		mockMvc.perform(get("/api/v1/users")
						.with(httpBasic(testUsername, testPassword))
						.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].username", is("testUser")))
				.andExpect(jsonPath("$[0].email", is("test@example.com")));
	}

	@Test
	void testGetUserById() throws Exception {
		User user = new User();
		user.setId(1L);
		user.setUsername("testUser");
		user.setEmail("test@example.com");

		when(userService.getUserById(1L)).thenReturn(Optional.of(user));
		when(userService.getUserById(2L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/api/v1/users/1")
						.with(httpBasic(testUsername, testPassword))
						.with(csrf()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.username", is("testUser")))
				.andExpect(jsonPath("$.email", is("test@example.com")));

		// Test not found scenario
		mockMvc.perform(get("/api/v1/users/2")
						.with(httpBasic(testUsername, testPassword)))
				.andExpect(status().isNotFound());
	}
}
