package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.AddressDTO;
import com.tresin.cvproj.handmade_shop.model.Address;
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

/**
 * The Address API provides endpoints for managing addresses.
 */
@Tag(name = "Address", description = "The Address API provides endpoints for managing addresses.")
@RequestMapping("/api/v1/addresses")
public interface AddressApi {

	/**
	 * Creates a new address.
	 *
	 * @param addressDTO The DTO containing the address information to be created.
	 * @return ResponseEntity<Address> The created address.
	 */
	@Operation(
			summary = "Create address",
			description = "Creates a new address")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO);

	/**
	 * Updates an existing address.
	 *
	 * @param id          The ID of the address to update.
	 * @param addressDTO  The DTO containing the updated address information.
	 * @return ResponseEntity<Address> The updated address.
	 */
	@Operation(
			summary = "Update address",
			description = "Updates an existing address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Address not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO);

	/**
	 * Deletes an existing address.
	 *
	 * @param id The ID of the address to delete.
	 * @return ResponseEntity<Void> No content in the response body.
	 */
	@Operation(
			summary = "Delete address",
			description = "Deletes an existing address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Address not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteAddress(@PathVariable Long id);

	/**
	 * Retrieves all addresses.
	 *
	 * @return ResponseEntity<List<Address>> A list containing all addresses.
	 */
	@Operation(
			summary = "Get all addresses",
			description = "Retrieves all addresses")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Address>> getAllAddresses();

	/**
	 * Retrieves an address by its ID.
	 *
	 * @param id The ID of the address to retrieve.
	 * @return ResponseEntity<Address> The address with the given ID.
	 */
	@Operation(
			summary = "Get address by ID",
			description = "Retrieves an address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Address not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Address> getAddressById(@PathVariable Long id);


	/**
	 * Retrieves the address associated with a specific user.
	 *
	 * @param userId The ID of the user whose address is to be retrieved.
	 * @return ResponseEntity<Address> The address associated with the given user ID.
	 */
	@Operation(
			summary = "Get address by user ID",
			description = "Retrieves the address associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid user ID provided"),
			@ApiResponse(responseCode = "404", description = "User or Address not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<Address> getAddressByUserId(@PathVariable Long userId);
}
