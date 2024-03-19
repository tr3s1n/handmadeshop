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

@Tag(name = "Role", description = "the Role Api")
@RequestMapping("/api/v1/roles")
public interface RoleApi {

	@Operation(summary = "Create role", description = "Creates a new role")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping("/roles")
	ResponseEntity<Role> createRole(@RequestBody Role role);

	@Operation(summary = "Update role", description = "Updates an existing role by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Role not found")
	})
	@PutMapping("/roles/{id}")
	ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role);

	@Operation(summary = "Delete role", description = "Deletes an existing role by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Role not found")
	})
	@DeleteMapping("/roles/{id}")
	ResponseEntity<Void> deleteRole(@PathVariable Long id);

	@Operation(summary = "Get all roles", description = "Retrieves all roles")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/roles")
	ResponseEntity<List<Role>> getAllRoles();

	@Operation(summary = "Get role by ID", description = "Retrieves a role by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Role not found")
	})
	@GetMapping("/roles/{id}")
	ResponseEntity<Role> getRoleById(@PathVariable Long id);
}