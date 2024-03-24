package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Payment;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotEmpty(message = "Products list cannot be empty")
    private List<Product> products;
    @NotBlank(message = "Payment is required")
    private Payment payment;

    /**
     * Converts the OrderDTO object to an Order object.
     *
     * @return The Order object converted from the OrderDTO.
     * @throws IllegalArgumentException If the user, products or payment is null.
     */
    public Order toOrder() {
        if (this.user == null || this.payment == null || this.products == null || this.products.isEmpty()) {
            throw new IllegalArgumentException("Invalid OrderDTO: User, products and payment are required");
        }

        Order order = new Order();
        order.setUser(this.getUser());
        order.setProducts(this.getProducts());
        order.setPayment(this.getPayment());
        return order;
    }
}
