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
    public Review createReview(Review newReview) {
        Review createdReview = reviewRepository.save(newReview);
        logger.info("Review with ID {} created successfully", createdReview.getId());
        return createdReview;
    }

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

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

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

    // TODO: implement
	public List<Review> getReviewsByUserId(Long userId) {
        return null;
	}
}
