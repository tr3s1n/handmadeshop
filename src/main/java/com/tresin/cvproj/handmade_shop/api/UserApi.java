package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.UserDTO;
import com.tresin.cvproj.handmade_shop.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Tag(name = "User", description = "the User Api")
@RequestMapping("/api/v1/users")
public interface UserApi {

	@Operation(
			summary = "Create user",
			description = "Creates a new user")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO);

	@Operation(
			summary = "Update user",
			description = "Updates an existing user by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO);

	@Operation(
			summary = "Delete user",
			description = "Deletes an existing user by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable Long id);

	@Operation(
			summary = "Get all users",
			description = "Retrieves all users")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<User>> getAllUsers();

	@Operation(
			summary = "Get user by ID",
			description = "Retrieves an user by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable Long id);
	
}
