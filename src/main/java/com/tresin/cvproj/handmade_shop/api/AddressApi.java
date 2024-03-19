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

@Tag(name = "Address", description = "the Address Api")
@RequestMapping("/api/v1/addresses")
public interface AddressApi {

	@Operation(
			summary = "Create address",
			description = "Creates a new address")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO);

	@Operation(
			summary = "Update address",
			description = "Updates an existing address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Address not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO);

	@Operation(
			summary = "Delete address",
			description = "Deletes an existing address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Address not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteAddress(@PathVariable Long id);

	@Operation(
			summary = "Get all addresses",
			description = "Retrieves all addresses")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Address>> getAllAddresses();

	@Operation(
			summary = "Get address by ID",
			description = "Retrieves an address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Address not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Address> getAddressById(@PathVariable Long id);

	@Operation(
			summary = "Get address by user ID",
			description = "Retrieves the address associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found or no address found")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<Address> getAddressByUserId(@PathVariable Long userId);
}
