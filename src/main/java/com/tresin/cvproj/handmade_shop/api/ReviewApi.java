package com.tresin.cvproj.handmade_shop.api;

import com.tresin.cvproj.handmade_shop.dto.ReviewDTO;
import com.tresin.cvproj.handmade_shop.model.Review;
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
 * The Review API provides endpoints for managing reviews.
 */
@Tag(name = "Review", description = "The Review API provides endpoints for managing reviews.")
@RequestMapping("/api/v1/reviews")
public interface ReviewApi {

	/**
	 * Creates a new review.
	 *
	 * @param reviewDTO The review data to create.
	 * @return ResponseEntity<Review> The created review.
	 */
	@Operation(
			summary = "Create review",
			description = "Creates a new review"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PostMapping
	ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDTO reviewDTO);

	/**
	 * Updates an existing review by its ID.
	 *
	 * @param id        The ID of the review to update.
	 * @param reviewDTO The updated review data.
	 * @return ResponseEntity<Review> The updated review.
	 */
	@Operation(
			summary = "Update review",
			description = "Updates an existing review by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid data provided"),
			@ApiResponse(responseCode = "404", description = "Review not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@PutMapping("/{id}")
	ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO);

	/**
	 * Deletes an existing review by its ID.
	 *
	 * @param id The ID of the review to delete.
	 * @return ResponseEntity<Void> A response entity indicating the success of the operation.
	 */
	@Operation(
			summary = "Delete review",
			description = "Deletes an existing review by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Review not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteReview(@PathVariable Long id);

	/**
	 * Retrieves all reviews.
	 *
	 * @return ResponseEntity<List<Review>> A list containing all reviews.
	 */
	@Operation(
			summary = "Get all reviews",
			description = "Retrieves all reviews"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping
	ResponseEntity<List<Review>> getAllReviews();

	/**
	 * Retrieves a review by its ID.
	 *
	 * @param id The ID of the review to retrieve.
	 * @return ResponseEntity<Review> The review with the given ID.
	 */
	@Operation(
			summary = "Get review by ID",
			description = "Retrieves a review by its ID"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid ID provided"),
			@ApiResponse(responseCode = "404", description = "Review not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/{id}")
	ResponseEntity<Review> getReviewById(@PathVariable Long id);


	/**
	 * Retrieves reviews associated with a specific user.
	 *
	 * @param userId The ID of the user to retrieve reviews for.
	 * @return ResponseEntity<List<Review>> A list containing reviews associated with the specified user.
	 */
	@Operation(
			summary = "Get reviews by user ID",
			description = "Retrieves reviews associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Successful operation"),
			@ApiResponse(responseCode = "400", description = "Bad request, invalid user ID provided"),
			@ApiResponse(responseCode = "404", description = "User not found"),
			@ApiResponse(responseCode = "500", description = "Internal server error")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId);
}
