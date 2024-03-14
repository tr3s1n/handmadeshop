package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.AddressDTO;
import com.tresin.cvproj.handmade_shop.model.Address;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

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
	@PutMapping("/{addressId}")
	ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @Valid @RequestBody AddressDTO addressDTO);

	@Operation(
			summary = "Delete address",
			description = "Deletes an existing address by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Address not found")
	})
	@DeleteMapping("/{addressId}")
	ResponseEntity<Void> deleteAddress(@PathVariable Long addressId);

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
	@GetMapping("/{addressId}")
	ResponseEntity<Address> getAddressById(@PathVariable Long addressId);
}
