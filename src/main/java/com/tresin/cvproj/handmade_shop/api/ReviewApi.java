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

@Tag(name = "Review", description = "the Review Api")
@RequestMapping("/api/v1/reviews")
public interface ReviewApi {

	@Operation(
			summary = "Create review",
			description = "Creates a new review")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@PostMapping
	ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDTO reviewDTO);

	@Operation(
			summary = "Update review",
			description = "Updates an existing review by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Review not found")
	})
	@PutMapping("/{id}")
	ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO);

	@Operation(
			summary = "Delete review",
			description = "Deletes an existing review by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Review not found")
	})
	@DeleteMapping("/{id}")
	ResponseEntity<Void> deleteReview(@PathVariable Long id);

	@Operation(
			summary = "Get all reviews",
			description = "Retrieves all reviews")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation")
	})
	@GetMapping
	ResponseEntity<List<Review>> getAllReviews();

	@Operation(
			summary = "Get review by ID",
			description = "Retrieves an review by its ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "Review not found")
	})
	@GetMapping("/{id}")
	ResponseEntity<Review> getReviewById(@PathVariable Long id);


	@Operation(
			summary = "Get reviews by user ID",
			description = "Retrieves reviews associated with a specific user"
	)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation"),
			@ApiResponse(responseCode = "404", description = "User not found")
	})
	@GetMapping("/user/{userId}")
	ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId);
}
