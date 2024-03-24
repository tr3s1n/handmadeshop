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

/**
 * The Product API provides endpoints for managing products.
 */
@Tag(name = "Product", description = "The Product API provides endpoints for managing products")
@RequestMapping("/api/v1/products")
public interface ProductApi {

	/**
	 * Creates a new product.
	 *
	 * @param productDTO The DTO containing the product information to be created.
	 * @return ResponseEntity<Product> The created product.
	 */
	@Operation(
			summary = "Create product",
			description = "Creates a new product")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid product data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO);

	/**
	 * Updates an existing product.
	 *
	 * @param id         The ID of the product to update.
	 * @param productDTO The DTO containing the updated product information.
	 * @return ResponseEntity<Product> The updated product.
	 */
	@Operation(
			summary = "Update product",
			description = "Updates an existing product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Product> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDTO productDTO);

	/**
	 * Deletes an existing product.
	 *
	 * @param id The ID of the product to delete.
	 * @return ResponseEntity<Void> No content in the response body.
	 */
	@Operation(
			summary = "Delete product",
			description = "Deletes an existing product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteProduct(@PathVariable Long id);

	/**
	 * Retrieves all products.
	 *
	 * @return ResponseEntity<List<Product>> A list containing all products.
	 */
	@Operation(
			summary = "Get all products",
			description = "Retrieves all products")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Product>> getAllProducts();

	/**
	 * Retrieves a product by its ID.
	 *
	 * @param id The ID of the product to retrieve.
	 * @return ResponseEntity<Product> The product with the given ID.
	 */
	@Operation(
			summary = "Get product by ID",
			description = "Retrieves an product by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Product> getProductById(@PathVariable Long id);

	/**
	 * Searches for products based on keyword, category, minPrice, and maxPrice.
	 *
	 * @param keyword  The keyword to search for.
	 * @param category The category to filter the products.
	 * @param minPrice The minimum price of the products.
	 * @param maxPrice The maximum price of the products.
	 * @return ResponseEntity<List<Product>> A list containing the filtered products.
	 */
	@Operation(
			summary = "Search products",
			description = "Searches for products based on keyword, category, minPrice, and maxPrice"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/search")
	ResponseEntity<List<Product>> searchProducts(@RequestParam(required = false) String keyword,
												 @RequestParam(required = false) String category,
												 @RequestParam(required = false) Double minPrice,
												 @RequestParam(required = false) Double maxPrice);

	/**
	 * Filters products based on category, color, ...
	 *
	 * @param category The category to filter the products.
	 * @param color    The color to filter the products.
	 * @return ResponseEntity<List<Product>> A list containing the filtered products.
	 */
	@Operation(
			summary = "Filter products",
			description = "Filters products based on category, color, ..."
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/filter")
	ResponseEntity<List<Product>> filterProducts(@RequestParam(required = false) String category,
												 @RequestParam(required = false) String color);

	/**
	 * Sorts products based on specified criteria.
	 *
	 * @param sortBy The criteria to sort the products.
	 * @return ResponseEntity<List<Product>> A list containing the sorted products.
	 */
	@Operation(
			summary = "Sort products",
			description = "Sorts products based on specified criteria"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/sort")
	ResponseEntity<List<Product>> sortProducts(@RequestParam String sortBy);

	/**
	 * Retrieves products belonging to a particular category.
	 *
	 * @param categoryId The ID of the category to retrieve products.
	 * @return ResponseEntity<List<Product>> A list containing products of the specified category.
	 */
	@Operation(
			summary = "Get products by category",
			description = "Retrieves products belonging to a particular category"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid category ID"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/category/{categoryId}")
	ResponseEntity<List<Product>> getProductsByCategoryId(@PathVariable Long categoryId);

	/**
	 * Retrieves a product associated with a specific image.
	 *
	 * @param imageId The ID of the image associated with the product.
	 * @return ResponseEntity<Product> The product associated with the given image ID.
	 */
	@Operation(
			summary = "Get product by image ID",
			description = "Retrieves a product associated with a specific image"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid image ID"),
			@ApiResponse(responseCode = "404", description = "Image not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/image/{imageId}")
	ResponseEntity<Product> getProductByImageId(@PathVariable Long imageId);

	/**
	 * Retrieves a product associated with a specific review.
	 *
	 * @param reviewId The ID of the review associated with the product.
	 * @return ResponseEntity<Product> The product associated with the given review ID.
	 */
	@Operation(
			summary = "Get product by review ID",
			description = "Retrieves a product associated with a specific review"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid review ID"),
			@ApiResponse(responseCode = "404", description = "Review not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/review/{reviewId}")
	ResponseEntity<Product> getProductByReviewId(@PathVariable Long reviewId);

	/**
	 * Retrieves all products associated with a specific cart.
	 *
	 * @param cartId The ID of the cart to retrieve products.
	 * @return ResponseEntity<List<Product>> A list containing products associated with the specified cart.
	 */
	@Operation(
			summary = "Get products by cart ID",
			description = "Retrieves all products associated with a specific cart"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid cart ID"),
			@ApiResponse(responseCode = "404", description = "Cart not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/cart/{cartId}")
	ResponseEntity<List<Product>> getProductsByCartId(@PathVariable Long cartId);

	/**
	 * Retrieves all products associated with a specific order.
	 *
	 * @param orderId The ID of the order to retrieve products.
	 * @return ResponseEntity<List<Product>> A list containing products associated with the specified order.
	 */
	@Operation(
			summary = "Get products by order ID",
			description = "Retrieves all products associated with a specific order"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid order ID"),
			@ApiResponse(responseCode = "404", description = "Order not found or no products found in the order"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/order/{orderId}")
	ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long orderId);
}