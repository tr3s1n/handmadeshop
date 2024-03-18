package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.ImageDTO;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
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

@Tag(name = "Image", description = "the Image Api")
@RequestMapping("/api/v1/images")
public interface ImageApi {

	@Operation(
			summary = "Create image",
			description = "Creates a new image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Image> createImage(@Valid @RequestBody ImageDTO imageDTO);

	@Operation(
			summary = "Update image",
			description = "Updates an existing image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Image not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Image> updateImage(@PathVariable Long id, @Valid @RequestBody ImageDTO imageDTO);

	@Operation(
			summary = "Delete image",
			description = "Deletes an existing image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Image not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteImage(@PathVariable Long id);

	@Operation(
			summary = "Get all images",
			description = "Retrieves all images")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Image>> getAllImages();

	@Operation(
			summary = "Get image by ID",
			description = "Retrieves an image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Image not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Image> getImageById(@PathVariable Long id);

	@Operation(
			summary = "Get product by image ID",
			description = "Retrieves the product associated with the image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Image or product not found")
	})
	@GetMapping("/{id}/product")
	ResponseEntity<Product> getProductByImageId(@PathVariable Long id);
}
