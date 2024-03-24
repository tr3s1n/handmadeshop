package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.UserDTO;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.Role;
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
import java.util.Set;

/**
 * The User API provides endpoints for managing users.
 */
@Tag(name = "User", description = "The User API provides endpoints for managing users")
@RequestMapping("/api/v1/users")
public interface UserApi {

	/**
	 * Creates a new user.
	 *
	 * @param userDTO The user data to create.
	 * @return ResponseEntity<User> The created user.
	 */
	@Operation(
			summary = "Create user",
			description = "Creates a new user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<User> createUser(@Valid @RequestBody UserDTO userDTO);

	/**
	 * Updates an existing user by its ID.
	 *
	 * @param id      The ID of the user to update.
	 * @param userDTO The updated user data.
	 * @return ResponseEntity<User> The updated user.
	 */
	@Operation(
			summary = "Update user",
			description = "Updates an existing user by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<User> updateUser(@PathVariable Long id, @Valid @RequestBody UserDTO userDTO);

	/**
	 * Deletes an existing user by its ID.
	 *
	 * @param id The ID of the user to delete.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Delete user",
			description = "Deletes an existing user by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteUser(@PathVariable Long id);

	/**
	 * Retrieves all users.
	 *
	 * @return ResponseEntity<List<User>> A list containing all users.
	 */
	@Operation(
			summary = "Get all users",
			description = "Retrieves all users"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<User>> getAllUsers();

	/**
	 * Retrieves a user by its ID.
	 *
	 * @param id The ID of the user to retrieve.
	 * @return ResponseEntity<User> The user with the given ID.
	 */
	@Operation(
			summary = "Get user by ID",
			description = "Retrieves a user by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<User> getUserById(@PathVariable Long id);

	/**
	 * Updates the password of an existing user.
	 *
	 * @param id          The ID of the user.
	 * @param newPassword The new password.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Update user password",
			description = "Updates the password of an existing user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID or new password provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}/password")
	ResponseEntity<Void> updateUserPassword(@PathVariable Long id, @Valid @RequestBody String newPassword);

	/**
	 * Retrieves the orders placed by a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<List<Order>> A list containing the orders of the user.
	 */
	@Operation(
			summary = "Get user orders",
			description = "Retrieves the orders placed by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}/orders")
	ResponseEntity<List<Order>> getOrdersByUserId(@PathVariable Long id);

	/**
	 * Retrieves the reviews submitted by a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<List<Review>> A list containing the reviews submitted by the user.
	 */
	@Operation(
			summary = "Get user reviews",
			description = "Retrieves the reviews submitted by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}/reviews")
	ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long id);

	/**
	 * Retrieves the address associated with a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<Address> The address associated with the user.
	 */
	@Operation(
			summary = "Get user address",
			description = "Retrieves the address associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User or address not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}/address")
	ResponseEntity<Address> getAddressByUserId(@PathVariable Long id);

	/**
	 * Retrieves the cart items associated with a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<Cart> The cart associated with the user.
	 */
	@Operation(
			summary = "Get user cart",
			description = "Retrieves the cart items associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User or cart found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}/cart")
	ResponseEntity<Cart> getCartByUserId(@PathVariable Long id);

	/**
	 * Assigns a role to a specific user.
	 *
	 * @param userId The ID of the user.
	 * @param roleId The ID of the role.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Add role to user",
			description = "Assigns a role to a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User or role not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/{userId}/roles/{roleId}")
	ResponseEntity<Void> addRoleToUser(@PathVariable Long userId, @PathVariable Long roleId);

	/**
	 * Removes a role from a specific user.
	 *
	 * @param userId The ID of the user.
	 * @param roleId The ID of the role to be removed.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Remove role from user",
			description = "Removes a role from a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User or role not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{userId}/roles/{roleId}")
	ResponseEntity<Void> removeRoleFromUser(@PathVariable Long userId, @PathVariable Long roleId);

	/**
	 * Retrieves the roles assigned to a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<Set<Role>> A set containing the roles assigned to the user.
	 */
	@Operation(
			summary = "Get user role",
			description = "Retrieves the roles assigned to a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}/roles")
	ResponseEntity<Set<Role>> getRolesByUserId(@PathVariable Long id);

	/**
	 * Retrieves a user by their username.
	 *
	 * @param username The username of the user.
	 * @return ResponseEntity<User> The user with the specified username.
	 */
	@Operation(
			summary = "Get user by username",
			description = "Retrieves a user by their username"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid username provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/username/{username}")
	ResponseEntity<User> getUserByUsername(@PathVariable String username);

	/**
	 * Retrieves a user by their email address.
	 *
	 * @param email The email address of the user.
	 * @return ResponseEntity<User> The user with the specified email address.
	 */
	@Operation(
			summary = "Get user by email",
			description = "Retrieves a user by their email address"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid email provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/email/{email}")
	ResponseEntity<User> getUserByEmail(@PathVariable String email);

	/**
	 * Deletes all reviews submitted by a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Delete user reviews",
			description = "Deletes all reviews submitted by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}/reviews")
	ResponseEntity<Void> deleteUserReviews(@PathVariable Long id);

	/**
	 * Cancels all orders placed by a specific user.
	 *
	 * @param id The ID of the user.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Delete user orders",
			description = "Cancels all orders placed by a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}/orders")
	ResponseEntity<Void> deleteUserOrders(@PathVariable Long id);
}