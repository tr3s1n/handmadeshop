package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
import com.tresin.cvproj.handmade_shop.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Product", description = "the Product Api")
@RequestMapping("/api/v1/products")
public interface ProductApi {

	@Operation(
			summary = "Create product",
			description = "Creates a new product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO);

	@Operation(
			summary = "Update product",
			description = "Updates an existing product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Product not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO);

	@Operation(
			summary = "Delete product",
			description = "Deletes an existing product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Product not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteProduct(@PathVariable Long id);

	@Operation(
			summary = "Get all products",
			description = "Retrieves all products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Product>> getAllProducts();

	@Operation(
			summary = "Get product by ID",
			description = "Retrieves an product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Product not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Product> getProductById(@PathVariable Long id);

	@Operation(
			summary = "Search products",
			description = "Searches for products based on keyword, category, minPrice, and maxPrice"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/search")
	ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String keyword,
												 @RequestParam(required = false) String category,
												 @RequestParam(required = false) Double minPrice,
												 @RequestParam(required = false) Double maxPrice);

	@Operation(
			summary = "Filter products",
			description = "Filters products based on category, color, ..."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/filter")
	ResponseEntity<List<Product>> filterProducts(@RequestParam(required = false) String category,
												 @RequestParam(required = false) String color);

	@Operation(
			summary = "Sort products",
			description = "Sorts products based on specified criteria"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/sort")
	ResponseEntity<List<Product>> sortProducts(@RequestParam String sortBy);

	@Operation(
			summary = "Get products by category",
			description = "Retrieves products belonging to a particular category"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/category/{categoryId}")
	ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId);

	@Operation(
			summary = "Get product by image ID",
			description = "Retrieves a product associated with a specific image"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/image/{imageId}")
	ResponseEntity<Product> getProductByImageId(@PathVariable Long imageId);

	@Operation(
			summary = "Get product by review ID",
			description = "Retrieves a product associated with a specific review"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping("/review/{reviewId}")
	ResponseEntity<Product> getProductByReviewId(@PathVariable Long reviewId);

	@Operation(
			summary = "Get products by cart ID",
			description = "Retrieves all products associated with a specific cart"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Cart not found or no products in the cart")
	})
	@GetMapping("/cart/{cartId}")
	ResponseEntity<List<Product>> getProductsByCartId(@PathVariable Long cartId);

	@Operation(
			summary = "Get products by order ID",
			description = "Retrieves all products associated with a specific order"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Order not found or no products found in the order")
	})
	@GetMapping("/order/{orderId}")
	ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long orderId);
}
