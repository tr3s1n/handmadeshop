package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartDTO {

    @NotBlank(message = "User is required")
    private User user;
    private List<Product> products;

    /**
     * Converts the CartDTO object to a Cart object.
     *
     * @return The Cart object converted from the CartDTO.
     * @throws IllegalArgumentException If the user is null.
     */
    public Cart toCart() {
        if (this.user == null) {
            throw new IllegalArgumentException("Invalid CartDTO: User is required");
        }
        if (this.products == null) {
            this.products = Collections.emptyList();
        }

        Cart cart = new Cart();
        cart.setUser(this.getUser());
        cart.setProducts(this.getProducts());
        return cart;
    }
}
