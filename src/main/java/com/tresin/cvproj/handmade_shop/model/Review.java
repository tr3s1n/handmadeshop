package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.ReviewDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "User is required")
	@ManyToOne
	private User user;
	@NotBlank(message = "Product is required")
	@ManyToOne
	private Product product;
	@NotBlank(message = "Rating is required")
	private int rating;
	@NotBlank(message = "Comment is required")
	private String comment;

	public Review(User user, Product product, int rating, String comment) {
		this.user = user;
		this.product = product;
		this.rating = rating;
		this.comment = comment;
	}

	/**
	 * Updates the review entity based on the provided DTO.
	 *
	 * @param reviewDTO The DTO containing the updated review information.
	 */
	public void updateFromDTO(ReviewDTO reviewDTO) {
		this.setUser(reviewDTO.getUser());
		this.setProduct(reviewDTO.getProduct());
		this.setRating(reviewDTO.getRating());
		this.setComment(reviewDTO.getComment());
	}

	/**
	 * Converts the review entity to a DTO.
	 *
	 * @return The DTO representation of the review entity.
	 */
	public ReviewDTO toDTO() {
		ReviewDTO reviewDTO = new ReviewDTO();
		reviewDTO.setUser(this.user);
		reviewDTO.setProduct(this.product);
		reviewDTO.setRating(this.rating);
		reviewDTO.setComment(this.comment);
		return reviewDTO;
	}
}
