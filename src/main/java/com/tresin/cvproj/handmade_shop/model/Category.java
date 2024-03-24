package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.CategoryDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Name is required")
    private String name;
    @ManyToMany(mappedBy = "categories")
    private List<Product> products;

    public Category(String name) {
        this.name = name;
    }

    public Category(String name, List<Product> products) {
        this.name = name;
        this.products = products;
    }

    /**
     * Updates the category entity based on the provided DTO.
     *
     * @param categoryDTO The DTO containing the updated category information.
     */
    public void updateFromDTO(CategoryDTO categoryDTO) {
        this.setName(categoryDTO.getName());
        this.setProducts(categoryDTO.getProducts());
    }

    /**
     * Converts the category entity to a DTO.
     *
     * @return The DTO representation of the category entity.
     */
    public CategoryDTO toDTO() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setName(this.getName());
        categoryDTO.setProducts(this.getProducts());
        return categoryDTO;
    }
}
