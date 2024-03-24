package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.CategoryDTO;
import com.tresin.cvproj.handmade_shop.model.Category;
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
 * The Category API provides endpoints for managing categories.
 */
@Tag(name = "Category", description = "The Category API provides endpoints for managing categories.")
@RequestMapping("/api/v1/categories")
public interface CategoryApi {

	/**
	 * Creates a new category.
	 *
	 * @param categoryDTO The DTO containing the category information to be created.
	 * @return ResponseEntity<Category> The created category.
	 */
	@Operation(
			summary = "Create category",
			description = "Creates a new category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO);

	/**
	 * Updates an existing category.
	 *
	 * @param id          The ID of the category to update.
	 * @param categoryDTO The DTO containing the updated category information.
	 * @return ResponseEntity<Category> The updated category.
	 */
	@Operation(
			summary = "Update category",
			description = "Updates an existing category by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO);

	/**
	 * Deletes an existing category.
	 *
	 * @param id The ID of the category to delete.
	 * @return ResponseEntity<Void> No content in the response body.
	 */
	@Operation(
			summary = "Delete category",
			description = "Deletes an existing category by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteCategory(@PathVariable Long id);

	/**
	 * Retrieves all categories.
	 *
	 * @return ResponseEntity<List<Category>> A list containing all categories.
	 */
	@Operation(
			summary = "Get all categories",
			description = "Retrieves all categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Category>> getAllCategories();

	/**
	 * Retrieves a category by its ID.
	 *
	 * @param id The ID of the category to retrieve.
	 * @return ResponseEntity<Category> The category with the given ID.
	 */
	@Operation(
			summary = "Get category by ID",
			description = "Retrieves an category by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Category not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Category> getCategoryById(@PathVariable Long id);

	/**
	 * Retrieves the categories associated with a specific product.
	 *
	 * @param productId The ID of the product whose categories are to be retrieved.
	 * @return ResponseEntity<List<Category>> The list of categories associated with the given product ID.
	 */
	@Operation(
			summary = "Get categories by product ID",
			description = "Retrieves all categories associated with a specific product"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid product ID provided"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/product/{productId}")
	ResponseEntity<List<Category>> getCategoriesByProductId(@PathVariable Long productId);
}
