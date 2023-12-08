package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.ReviewDTO;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@Valid @RequestBody ReviewDTO reviewDTO) {
        Review newReview = new Review();
        newReview.setUser(reviewDTO.getUser());
        newReview.setProduct(reviewDTO.getProduct());
        newReview.setRating(reviewDTO.getRating());
        newReview.setComment(reviewDTO.getComment());
        Review createdReview = reviewService.createReview(newReview);

        return ResponseEntity.ok(createdReview);
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateReview(@PathVariable Long reviewId, @Valid @RequestBody ReviewDTO reviewDTO) {
        Review updatedReview = new Review();
        updatedReview.setUser(reviewDTO.getUser());
        updatedReview.setProduct(reviewDTO.getProduct());
        updatedReview.setRating(reviewDTO.getRating());
        updatedReview.setComment(reviewDTO.getComment());
        Review resultReview = reviewService.updateReview(reviewId, updatedReview);

        if (resultReview != null) {
            return ResponseEntity.ok(resultReview);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable Long reviewId) {
        reviewService.deleteReview(reviewId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();

        return ResponseEntity.ok(reviews);
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getReviewById(@PathVariable Long reviewId) {
        return reviewService.getReviewById(reviewId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
