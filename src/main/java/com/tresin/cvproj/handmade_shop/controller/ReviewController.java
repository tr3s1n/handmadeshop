package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ReviewApi;
import com.tresin.cvproj.handmade_shop.dto.ReviewDTO;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ReviewController implements ReviewApi {

	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@Override
	public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
		Review createdReview = reviewService.createReview(reviewDTO.toReview());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdReview);
	}

	@Override
	public ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO) {
		Review updatedReview = reviewService.createReview(reviewDTO.toReview());
		Review resultReview = reviewService.updateReview(id, updatedReview);
		return ResponseEntity.ok(resultReview);
	}

	@Override
	public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Review>> getAllReviews() {
		return ResponseEntity.ok(reviewService.getAllReviews());
	}

	@Override
	public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
		return ResponseEntity.ok(reviewService.getReviewById(id).orElseThrow());
	}

	@Override
	public ResponseEntity<List<Review>> getReviewsByUserId(@PathVariable Long userId) {
		return ResponseEntity.ok(reviewService.getReviewsByUserId(userId));
	}

}
