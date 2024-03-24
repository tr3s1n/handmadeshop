package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.ImageDTO;
import com.tresin.cvproj.handmade_shop.model.Image;
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
 * The Image API provides endpoints for managing images.
 */
@Tag(name = "Image", description = "The Image API provides endpoints for managing images.")
@RequestMapping("/api/v1/images")
public interface ImageApi {

	/**
	 * Creates a new image.
	 *
	 * @param imageDTO The DTO containing the image information to be created.
	 * @return ResponseEntity<Image> The created image.
	 */
	@Operation(
			summary = "Create image",
			description = "Creates a new image")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Image> createImage(@Valid @RequestBody ImageDTO imageDTO);

	/**
	 * Updates an existing image.
	 *
	 * @param id        The ID of the image to update.
	 * @param imageDTO  The DTO containing the updated image information.
	 * @return ResponseEntity<Image> The updated image.
	 */
	@Operation(
			summary = "Update image",
			description = "Updates an existing image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Image not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Image> updateImage(@PathVariable Long id, @Valid @RequestBody ImageDTO imageDTO);

	/**
	 * Deletes an existing image.
	 *
	 * @param id The ID of the image to delete.
	 * @return ResponseEntity<Void> No content in the response body.
	 */
	@Operation(
			summary = "Delete image",
			description = "Deletes an existing image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Image not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteImage(@PathVariable Long id);

	/**
	 * Retrieves all images.
	 *
	 * @return ResponseEntity<List<Image>> A list containing all images.
	 */
	@Operation(
			summary = "Get all images",
			description = "Retrieves all images")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Image>> getAllImages();

	/**
	 * Retrieves an image by its ID.
	 *
	 * @param id The ID of the image to retrieve.
	 * @return ResponseEntity<Image> The image with the given ID.
	 */
	@Operation(
			summary = "Get image by ID",
			description = "Retrieves an image by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Image not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Image> getImageById(@PathVariable Long id);

	/**
	 * Retrieves the images associated with a specific product.
	 *
	 * @param productId The ID of the product whose images are to be retrieved.
	 * @return ResponseEntity<List<Image>> The list of images associated with the given product ID.
	 */
	@Operation(
			summary = "Get images by product ID",
			description = "Retrieves images associated with a specific product"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid product ID provided"),
			@ApiResponse(responseCode = "404", description = "Product not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/product/{productId}")
	ResponseEntity<List<Image>> getImagesByProductId(@PathVariable Long productId);
}
