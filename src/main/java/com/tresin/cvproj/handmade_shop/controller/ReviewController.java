package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.ReviewApi;
import com.tresin.cvproj.handmade_shop.dto.ReviewDTO;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
		Review newReview = new Review();
		newReview.setUser(reviewDTO.getUser());
		newReview.setProduct(reviewDTO.getProduct());
		newReview.setRating(reviewDTO.getRating());
		newReview.setComment(reviewDTO.getComment());
		Review createdReview = reviewService.createReview(newReview);

		return ResponseEntity.ok(createdReview);
	}

	@Override
	public ResponseEntity<Review> updateReview(@PathVariable Long id, @Valid @RequestBody ReviewDTO reviewDTO) {
		Review updatedReview = new Review();
		updatedReview.setUser(reviewDTO.getUser());
		updatedReview.setProduct(reviewDTO.getProduct());
		updatedReview.setRating(reviewDTO.getRating());
		updatedReview.setComment(reviewDTO.getComment());
		Review resultReview = reviewService.updateReview(id, updatedReview);

		if (resultReview != null) {
			return ResponseEntity.ok(resultReview);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteReview(@PathVariable Long id) {
		reviewService.deleteReview(id);

		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Review>> getAllReviews() {
		List<Review> reviews = reviewService.getAllReviews();

		return ResponseEntity.ok(reviews);
	}

	@Override
	public ResponseEntity<Review> getReviewById(@PathVariable Long id) {
		return reviewService.getReviewById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
