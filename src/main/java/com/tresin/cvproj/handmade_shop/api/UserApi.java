package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.UserDTO;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@Tag(name = "User", description = "the User Api")
@RequestMapping("/api/v1/users")
public interface UserApi {

	@Operation(
			summary = "Create user",
			description = "Creates a new user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO);

	@Operation(
			summary = "Update user",
			description = "Updates an existing user by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO);

	@Operation(
			summary = "Delete user",
			description = "Deletes an existing user by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable Long id);

	@Operation(
			summary = "Get all users",
			description = "Retrieves all users"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<User>> getAllUsers();

	@Operation(
			summary = "Get user by ID",
			description = "Retrieves a user by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable Long id);

	@Operation(
			summary = "Update user password",
			description = "Updates the password of an existing user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@PutMapping("/{id}/password")
	ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @Valid @RequestBody String newPassword);

	@Operation(
			summary = "Get user orders",
			description = "Retrieves the orders placed by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no orders found")
	})
	@GetMapping("/{id}/orders")
	ResponseEntity<List<Order>> getUserOrders(@PathVariable Long id);

	@Operation(
			summary = "Get user reviews",
			description = "Retrieves the reviews submitted by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no reviews found")
	})
	@GetMapping("/{id}/reviews")
	ResponseEntity<List<Review>> getUserReviews(@PathVariable Long id);

	@Operation(
			summary = "Get user address",
			description = "Retrieves the address associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no address found")
	})
	@GetMapping("/{id}/address")
	ResponseEntity<Address> getUserAddress(@PathVariable Long id);

	@Operation(
			summary = "Get user cart",
			description = "Retrieves the cart items associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no cart found")
	})
	@GetMapping("/{id}/cart")
	ResponseEntity<Cart> getUserCart(@PathVariable Long id);

	@Operation(
			summary = "Add role to user",
			description = "Assigns a role to a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User or role not found")
	})
	@PostMapping("/{userId}/roles/{roleId}")
	ResponseEntity<Void> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId);

	@Operation(
			summary = "Remove role from user",
			description = "Removes a role from a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User or role not found")
	})
	@DeleteMapping("/{userId}/roles/{roleId}")
	ResponseEntity<Void> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId);

	@Operation(
			summary = "Get user role",
			description = "Retrieves the roles assigned to a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no roles found")
	})
	@GetMapping("/{id}/roles")
	ResponseEntity<Set<Role>> getUserRoles(@PathVariable Long id);

	@Operation(
			summary = "Get user by username",
			description = "Retrieves a user by their username"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/username/{username}")
	ResponseEntity<User> getUserByUsername(@PathVariable String username);

	@Operation(
			summary = "Get user by email",
			description = "Retrieves a user by their email address"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/email/{email}")
	ResponseEntity<User> getUserByEmail(@PathVariable String email);

	@Operation(
			summary = "Delete user reviews",
			description = "Deletes all reviews submitted by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no reviews found")
	})
	@DeleteMapping("/{id}/reviews")
	ResponseEntity<Void> deleteUserReviews(@PathVariable Long id);

	@Operation(
			summary = "Delete user orders",
			description = "Cancels all orders placed by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no orders found")
	})
	@DeleteMapping("/{id}/orders")
	ResponseEntity<Void> deleteUserOrders(@PathVariable Long id);
}