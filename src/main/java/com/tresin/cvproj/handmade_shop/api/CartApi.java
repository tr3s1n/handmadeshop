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

/**
 * The Cart API provides endpoints for managing carts.
 */
@Tag(name = "Cart", description = "The Cart API provides endpoints for managing carts")
@RequestMapping("/api/v1/carts")
public interface CartApi {

	/**
	 * Creates a new cart.
	 *
	 * @param cartDTO The cart data to be created.
	 * @return The created cart.
	 */
	@Operation(
			summary = "Create cart",
			description = "Creates a new cart"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Cart> createCart(@Valid @RequestBody CartDTO cartDTO);

	/**
	 * Updates an existing cart by its ID.
	 *
	 * @param id      The ID of the cart to update.
	 * @param cartDTO The updated cart data.
	 * @return The updated cart.
	 */
	@Operation(
			summary = "Update cart",
			description = "Updates an existing cart by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Cart not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Cart> updateCart(@PathVariable Long id, @Valid @RequestBody CartDTO cartDTO);

	/**
	 * Deletes an existing cart by its ID.
	 *
	 * @param id The ID of the cart to delete.
	 * @return ResponseEntity with no content.
	 */
	@Operation(
			summary = "Delete cart",
			description = "Deletes an existing cart by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Cart not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteCart(@PathVariable Long id);

	/**
	 * Retrieves all carts.
	 *
	 * @return A list containing all carts.
	 */
	@Operation(
			summary = "Get all carts",
			description = "Retrieves all carts"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Cart>> getAllCarts();

	/**
	 * Retrieves a cart by its ID.
	 *
	 * @param id The ID of the cart to retrieve.
	 * @return ResponseEntity containing the cart if found.
	 */
	@Operation(
			summary = "Get cart by ID",
			description = "Retrieves a cart by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Cart not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Cart> getCartById(@PathVariable Long id);

	/**
	 * Retrieves a cart by user ID.
	 *
	 * @param userId The ID of the user whose cart is to be retrieved.
	 * @return ResponseEntity containing the cart if found.
	 */
	@Operation(
			summary = "Get cart by user ID",
			description = "Retrieves the cart associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid user ID provided"),
			@ApiResponse(responseCode = "404", description = "User or cart not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<Cart> getCartByUserId(@PathVariable Long userId);

	/**
	 * Retrieves the products in a cart by its ID.
	 *
	 * @param id The ID of the cart.
	 * @return ResponseEntity containing the list of products in the cart if found.
	 */
	@Operation(
			summary = "Get all products from the cart",
			description = "Retrieves all products currently in the cart"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Cart not found or no products in the cart"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}/products")
	ResponseEntity<List<Product>> getCartProducts(@PathVariable Long id);
}
