package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class OrderDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotEmpty(message = "Products list cannot be empty")
    private List<Product> products;
    @NotBlank(message = "Payment is required")
    private Payment payment;

}
