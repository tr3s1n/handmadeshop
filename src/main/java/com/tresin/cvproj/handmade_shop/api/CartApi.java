package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.CartDTO;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Product;
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

@Tag(name = "Cart", description = "the Cart Api")
@RequestMapping("/api/v1/carts")
public interface CartApi {

	@Operation(
			summary = "Create cart",
			description = "Creates a new cart")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Cart> createCart(@Valid @RequestBody CartDTO cartDTO);

	@Operation(
			summary = "Update cart",
			description = "Updates an existing cart by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Cart not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Cart> updateCart(@PathVariable Long id, @Valid @RequestBody CartDTO cartDTO);

	@Operation(
			summary = "Delete cart",
			description = "Deletes an existing cart by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Cart not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteCart(@PathVariable Long id);

	@Operation(
			summary = "Get all carts",
			description = "Retrieves all carts")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Cart>> getAllCarts();

	@Operation(
			summary = "Get cart by ID",
			description = "Retrieves an cart by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Cart not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Cart> getCartById(@PathVariable Long id);

	@Operation(
			summary = "Get cart by user ID",
			description = "Retrieves the cart associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User or cart not found")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId);

	@Operation(
			summary = "Get all products from the cart",
			description = "Retrieves all products currently in the cart"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Cart not found or no products in the cart")
	})
	@GetMapping("/{id}/products")
	ResponseEntity<List<Product>> getCartProducts(@PathVariable Long id);
}
