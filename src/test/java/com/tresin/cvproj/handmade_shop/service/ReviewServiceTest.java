package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ReviewServiceTest {

	@InjectMocks
	private ReviewService reviewService;

	@Mock
	private ReviewRepository reviewRepository;

	@Test
	public void testCreateReview() {
		Review review = new Review(new User("User1"), new Product("Test Product", 10.99), 5, "Very good.");
		when(reviewRepository.save(any(Review.class))).thenReturn(review);
		Review createdReview = reviewService.createReview(review);

		assertNotNull(createdReview);
	}

	@Test
	public void testDeleteReview() {
		long reviewIdToDelete = 1L;
		Review reviewToDelete = new Review(new User("User1"), new Product("Test Product", 10.99), 5, "Very good.");
		when(reviewRepository.findById(reviewIdToDelete)).thenReturn(Optional.of(reviewToDelete));
		reviewService.deleteReview(reviewIdToDelete);

		verify(reviewRepository, times(1)).deleteById(reviewIdToDelete);
	}

	@Test
	public void testUpdateReview() {
		long reviewIdToUpdate = 1L;
		Review existingReview = new Review(new User("User1"), new Product("Test Product", 10.99), 5, "Very good.");
		Review updatedReviewData = new Review(new User("User1"), new Product("Test Product", 10.99), 4, "Good, but not the best.");
		when(reviewRepository.findById(reviewIdToUpdate)).thenReturn(Optional.of(existingReview));
		when(reviewRepository.save(any(Review.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Review updatedReview = reviewService.updateReview(reviewIdToUpdate, updatedReviewData);

		assertNotNull(updatedReview);
		assertEquals(updatedReviewData.getUser(), updatedReview.getUser());
		assertEquals(updatedReviewData.getProduct(), updatedReview.getProduct());
		assertEquals(updatedReviewData.getRating(), updatedReview.getRating());
		assertEquals(updatedReviewData.getComment(), updatedReview.getComment());
	}

	@Test
	public void testGetAllReviews() {
		List<Review> reviewList = Arrays.asList(
				new Review(new User("User1"), new Product("Test Product", 10.99), 5, "Very good."),
				new Review(new User("User2"), new Product("Test Product 2", 15.99), 4, "Good, but not the best.")
		);
		when(reviewRepository.findAll()).thenReturn(reviewList);
		List<Review> allReviews = reviewService.getAllReviews();

		assertNotNull(allReviews);
		assertEquals(reviewList.size(), allReviews.size());
	}

	@Test
	public void testGetReviewById() {
		long reviewIdToFind = 1L;
		Review foundReview = new Review(new User("User1"), new Product("Test Product", 10.99), 5, "Very good.");
		when(reviewRepository.findById(reviewIdToFind)).thenReturn(Optional.of(foundReview));
		Optional<Review> reviewById = reviewService.getReviewById(reviewIdToFind);

		assertTrue(reviewById.isPresent());
		assertEquals(foundReview, reviewById.get());
	}
}
