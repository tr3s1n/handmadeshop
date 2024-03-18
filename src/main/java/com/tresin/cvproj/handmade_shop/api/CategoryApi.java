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

@Tag(name = "Category", description = "the Category Api")
@RequestMapping("/api/v1/categories")
public interface CategoryApi {

	@Operation(
			summary = "Create category",
			description = "Creates a new category")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO);

	@Operation(
			summary = "Update category",
			description = "Updates an existing category by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Category not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Category> updateCategory(@PathVariable Long id, @Valid @RequestBody CategoryDTO categoryDTO);

	@Operation(
			summary = "Delete category",
			description = "Deletes an existing category by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Category not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteCategory(@PathVariable Long id);

	@Operation(
			summary = "Get all categories",
			description = "Retrieves all categories")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Category>> getAllCategories();

	@Operation(
			summary = "Get category by ID",
			description = "Retrieves an category by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Category not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Category> getCategoryById(@PathVariable Long id);
	
}
