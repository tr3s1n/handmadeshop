package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class OrderDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotEmpty(message = "Products list cannot be empty")
    private List<Product> products;
    @NotBlank(message = "Payment is required")
    private Payment payment;

    // Getters and setters
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
