package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

    @NotBlank(message = "Name is required")
    private String name;
    @NotBlank(message = "Prize is required")
    private double price;
    private List<Image> images;
    private List<Category> categories;

    /**
     * Converts the ProductDTO object to a Product object.
     *
     * @return The Product object converted from the ProductDTO.
     * @throws IllegalArgumentException If the name or price is null.
     */
    public Product toProduct() {
        if (this.name == null || this.price <= 0) {
            throw new IllegalArgumentException("Invalid ProductDTO: Name and price are required");
        }
        if (this.images == null) {
            this.images = Collections.emptyList();
        }
        if (this.categories == null) {
            this.categories = Collections.emptyList();
        }

        Product product = new Product();
        product.setName(this.getName());
        product.setPrice(this.getPrice());
        product.setImages(this.getImages());
        product.setCategories(this.getCategories());
        return product;
    }
}
