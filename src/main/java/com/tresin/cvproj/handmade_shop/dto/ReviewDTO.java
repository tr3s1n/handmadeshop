package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class ReviewDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotBlank(message = "Product is required")
    private Product product;
    @NotBlank(message = "Rating is required")
    private int rating;
    @NotBlank(message = "Comment is required")
    private String comment;

}
