package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Product;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class CategoryDTO {

    @NotBlank(message = "Name is required")
    private String name;
    private List<Product> products;

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
