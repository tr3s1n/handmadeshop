package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.ProductDTO;
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
	
}
