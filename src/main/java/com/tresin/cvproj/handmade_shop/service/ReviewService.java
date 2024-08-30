package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.ReviewNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.repository.ReviewRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);
    private final ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Creates a new review.
     *
     * @param newReview The review to be created.
     * @return The created review.
     */
    public Review createReview(Review newReview) {
        Review createdReview = reviewRepository.save(newReview);
        logger.info("Review with ID {} created successfully", createdReview.getId());
        return createdReview;
    }

    /**
     * Updates an existing review.
     *
     * @param reviewId      The ID of the review to update.
     * @param updatedReview The updated review information.
     * @return The updated review.
     * @throws ReviewNotFoundException If the review with the given ID is not found.
     */
    public Review updateReview(Long reviewId, Review updatedReview) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);
        if (existingReviewOptional.isPresent()) {
            Review existingReview = existingReviewOptional.get();
            existingReview.setUser(updatedReview.getUser());
            existingReview.setProduct(updatedReview.getProduct());
            existingReview.setRating(updatedReview.getRating());
            existingReview.setComment(updatedReview.getComment());

            Review savedReview = reviewRepository.save(existingReview);
            logger.info("Review with ID {} updated successfully", reviewId);

            return savedReview;
        } else {
            logger.warn("Review with ID {} not found in method updateReview", reviewId);
            throw new ReviewNotFoundException("Review with ID " + reviewId + " not found");
        }
    }

    /**
     * Deletes an existing review.
     *
     * @param reviewId The ID of the review to delete.
     * @throws ReviewNotFoundException If the review with the given ID is not found.
     */
    public void deleteReview(Long reviewId) {
        Optional<Review> existingReviewOptional = reviewRepository.findById(reviewId);
        if (existingReviewOptional.isPresent()) {
            reviewRepository.deleteById(reviewId);
            logger.info("Review with ID {} deleted successfully", reviewId);
        } else {
            logger.warn("Review with ID {} not found in method deleteReview", reviewId);
            throw new ReviewNotFoundException("Review with ID " + reviewId + " not found");
        }
    }

    /**
     * Retrieves all reviews.
     *
     * @return A list containing all reviews.
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves the review with the specified ID.
     *
     * @param reviewId The ID of the review to retrieve.
     * @return An Optional containing the review with the given ID, if present.
     * @throws ReviewNotFoundException If the review with the given ID is not found.
     */
    public Optional<Review> getReviewById(Long reviewId) {
        Optional<Review> reviewOptional = reviewRepository.findById(reviewId);
        if (reviewOptional.isPresent()) {
            logger.info("Review with ID {} found", reviewId);
        } else {
            logger.warn("Review with ID {} not found in method getReviewById", reviewId);
            throw new ReviewNotFoundException("Review with ID " + reviewId + " not found");
        }
        return reviewOptional;
    }

    /**
     * Retrieves reviews for a specific user identified by their ID.
     *
     * @param userId The ID of the user.
     * @return A list of reviews associated with the specified user.
     */
    public List<Review> getReviewsByUserId(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

}
