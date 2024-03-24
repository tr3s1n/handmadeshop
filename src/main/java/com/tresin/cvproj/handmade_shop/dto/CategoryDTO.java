package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Category;
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
public class CategoryDTO {

    @NotBlank(message = "Name is required")
    private String name;
    private List<Product> products;

    /**
     * Converts the CategoryDTO object to a Category object.
     *
     * @return The Category object converted from the CategoryDTO.
     * @throws IllegalArgumentException If name is null.
     */
    public Category toCategory() {
        if (this.name == null) {
            throw new IllegalArgumentException("Invalid CategoryDTO: Name is required");
        }
        if (this.products == null) {
            this.products = Collections.emptyList();
        }

        Category category = new Category();
        category.setName(this.getName());
        category.setProducts(this.getProducts());
        return category;
    }
}
