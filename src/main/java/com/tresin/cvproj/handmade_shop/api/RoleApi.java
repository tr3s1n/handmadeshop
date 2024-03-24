package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.model.Role;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * The Role API provides endpoints for managing roles.
 */
@Tag(name = "Role", description = "The Role API provides endpoints for managing roles.")
@RequestMapping("/api/v1/roles")
public interface RoleApi {

	/**
	 * Creates a new role.
	 *
	 * @param role The role data to create.
	 * @return ResponseEntity<Role> The created role.
	 */
	@Operation(summary = "Create role", description = "Creates a new role")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping("/roles")
	ResponseEntity<Role> createRole(@RequestBody Role role);

	/**
	 * Updates an existing role by its ID.
	 *
	 * @param id   The ID of the role to update.
	 * @param role The updated role data.
	 * @return ResponseEntity<Role> The updated role.
	 */
	@Operation(summary = "Update role", description = "Updates an existing role by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/roles/{id}")
	ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role);

	/**
	 * Deletes an existing role by its ID.
	 *
	 * @param id The ID of the role to delete.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(summary = "Delete role", description = "Deletes an existing role by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/roles/{id}")
	ResponseEntity<Void> deleteRole(@PathVariable Long id);

	/**
	 * Retrieves all roles.
	 *
	 * @return ResponseEntity<List<Role>> A list containing all roles.
	 */
	@Operation(summary = "Get all roles", description = "Retrieves all roles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/roles")
	ResponseEntity<List<Role>> getAllRoles();

	/**
	 * Retrieves a role by its ID.
	 *
	 * @param id The ID of the role to retrieve.
	 * @return ResponseEntity<Role> The role with the given ID.
	 */
	@Operation(summary = "Get role by ID", description = "Retrieves a role by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Role not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/roles/{id}")
	ResponseEntity<Role> getRoleById(@PathVariable Long id);
}
