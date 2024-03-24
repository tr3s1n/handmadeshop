package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotBlank(message = "Product is required")
    private Product product;
    @NotBlank(message = "Rating is required")
    private int rating;
    @NotBlank(message = "Comment is required")
    private String comment;

    /**
     * Converts the ReviewDTO object to a Review object.
     *
     * @return The Review object converted from the ReviewDTO.
     * @throws IllegalArgumentException If the user, product, rating or comment is null.
     */
    public Review toReview() {
        if (this.user == null || this.product == null || this.rating <= 0 || this.comment == null) {
            throw new IllegalArgumentException("Invalid ReviewDTO: User, product, rating and review are required");
        }

        Review review = new Review();
        review.setUser(this.getUser());
        review.setProduct(this.getProduct());
        review.setRating(this.getRating());
        review.setComment(this.getComment());
        return review;
    }
}
