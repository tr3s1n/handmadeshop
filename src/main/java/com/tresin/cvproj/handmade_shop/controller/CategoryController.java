package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.CategoryDTO;
import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@Valid @RequestBody CategoryDTO categoryDTO) {
        Category newCategory = new Category();
        newCategory.setName(categoryDTO.getName());
        newCategory.setProducts(categoryDTO.getProducts());
        Category createdCategory = categoryService.createCategory(newCategory);

        return ResponseEntity.ok(createdCategory);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long categoryId, @Valid @RequestBody CategoryDTO categoryDTO) {
        Category updatedCategory = new Category();
        updatedCategory.setName(categoryDTO.getName());
        updatedCategory.setProducts(categoryDTO.getProducts());
        Category resultCategory = categoryService.updateCategory(categoryId, updatedCategory);

        if (resultCategory != null) {
            return ResponseEntity.ok(resultCategory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId) {
        categoryService.deleteCategory(categoryId);

        return ResponseEntity.noContent().build();
    }

    // Again, not sure if this is good to have for any purpose
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.getAllCategories();

        return ResponseEntity.ok(categories);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long categoryId) {
        return categoryService.getCategoryById(categoryId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
