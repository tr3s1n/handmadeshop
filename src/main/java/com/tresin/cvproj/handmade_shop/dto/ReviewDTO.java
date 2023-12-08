package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class ReviewDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotBlank(message = "Product is required")
    private Product product;
    @NotBlank(message = "Rating is required")
    private int rating;
    @NotBlank(message = "Comment is required")
    private String comment;

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
