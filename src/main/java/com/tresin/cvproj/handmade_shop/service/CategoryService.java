package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.CategoryNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.repository.CategoryRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The CategoryService class provides operations for managing categories in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving categories.
 */
@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    /**
     * Creates a new category.
     *
     * @param newCategory The category to be created.
     * @return The created category.
     * @throws IllegalArgumentException      If the newCategory is null.
     * @throws ConstraintViolationException  If the newCategory violates constraints specified by annotations in the Category model class.
     */
    public Category createCategory(Category newCategory) {
        if (newCategory == null) {
            throw new IllegalArgumentException("Category cannot be null");
        }
        
        Category createdCategory = categoryRepository.save(newCategory);
        logger.info("Category created successfully: {}", createdCategory);
        return createdCategory;
    }

    /**
     * Updates an existing category.
     *
     * @param id              The ID of the category to update.
     * @param updatedCategory The updated category information.
     * @return The updated category.
     * @throws CategoryNotFoundException     If the category with the given ID is not found.
     * @throws IllegalArgumentException      If the category ID is null or invalid.
     * @throws ConstraintViolationException  If the newCategory violates constraints specified by annotations in the Category model class.
     */
    public Category updateCategory(Long id, Category updatedCategory) {
        if (id == null || id <= 0 || updatedCategory == null) {
            throw new IllegalArgumentException("Invalid category or ID");
        }

        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.updateFromDTO(updatedCategory.toDTO());
                    Category savedCategory = categoryRepository.save(existingCategory);
                    logger.info("Category with ID {} updated successfully", id);
                    return savedCategory;
                })
                .orElseThrow(() -> {
                    logger.error("Category with ID {} not found in method updateCategory", id);
                    return new CategoryNotFoundException("Category with ID " + id + " not found");
                });
    }

    /**
     * Deletes an existing category.
     *
     * @param id The ID of the category to delete.
     * @throws CategoryNotFoundException If the category with the given ID is not found.
     * @throws IllegalArgumentException  If the category ID is null or invalid.
     */
    public void deleteCategory(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid payment ID");
        }
        
        Optional<Category> categoryOptional = categoryRepository.findById(id);
        categoryOptional.ifPresentOrElse(
                category -> {
                    categoryRepository.deleteById(id);
                    logger.info("Category with ID {} deleted successfully", id);
                },
                () -> {
                    logger.error("Category with ID {} not found in method deleteCategory", id);
                    throw new CategoryNotFoundException("Category with ID " + id + " not found");
                }
        );
    }

    /**
     * Retrieves all categories.
     *
     * @return A list containing all categories.
     */
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    /**
     * Retrieves the category with the specified ID.
     *
     * @param id The ID of the category to retrieve.
     * @return An Optional containing the category with the given ID, if present.
     * @throws CategoryNotFoundException If the category with the given ID is not found.
     * @throws IllegalArgumentException  If the category ID is null or invalid.
     */
    public Optional<Category> getCategoryById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid category ID");
        }

        Optional<Category> categoryOptional = categoryRepository.findById(id);
        categoryOptional.ifPresentOrElse(
                category -> logger.info("Category with ID {} found", id),
                () -> {
                    logger.error("Category with ID {} not found in method getCategoryById", id);
                    throw new CategoryNotFoundException("Category with ID " + id + " not found");
                }
        );
        return categoryOptional;
    }

    /**
     * Retrieves the categories associated with the specified product ID.
     *
     * @param productId The ID of the product.
     * @return A list of categories associated with the product, or an empty list if no categories are found.
     * @throws IllegalArgumentException  If the product ID is null or invalid.
     * @throws CategoryNotFoundException If no categories are found for the specified product ID.
     */
    public List<Category> getCategoriesByProductId(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        List<Category> categories = categoryRepository.findCategoriesByProducts_Id(productId);
        if (categories.isEmpty()) {
            throw new CategoryNotFoundException("No categories found for product ID: " + productId);
        }
        return categories;
    }
}
