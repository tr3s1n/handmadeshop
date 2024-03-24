package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.CategoryNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Category;
import com.tresin.cvproj.handmade_shop.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Category createCategory(Category newCategory) {
        Category createdCategory = categoryRepository.save(newCategory);
        logger.info("Category with ID {} created successfully", createdCategory.getId());
        return createdCategory;
    }

    public Category updateCategory(Long categoryId, Category updatedCategory) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(categoryId);
        if (existingCategoryOptional.isPresent()) {
            Category existingCategory = existingCategoryOptional.get();
            existingCategory.setName(updatedCategory.getName());
            existingCategory.setProducts(updatedCategory.getProducts());

            Category savedCategory = categoryRepository.save(existingCategory);
            logger.info("Category with ID {} updated successfully", categoryId);

            return savedCategory;
        } else {
            logger.warn("Category with ID {} not found in method updateCategory", categoryId);
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
        }
    }

    public void deleteCategory(Long categoryId) {
        Optional<Category> existingCategoryOptional = categoryRepository.findById(categoryId);
        if (existingCategoryOptional.isPresent()) {
            categoryRepository.deleteById(categoryId);
            logger.info("Category with ID {} deleted successfully", categoryId);
        } else {
            logger.warn("Category with ID {} not found in method deleteCategory", categoryId);
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
        }
    }

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (categoryOptional.isPresent()) {
            logger.info("Category with ID {} found", categoryId);
        } else {
            logger.warn("Category with ID {} not found in method getCategoryById", categoryId);
            throw new CategoryNotFoundException("Category with ID " + categoryId + " not found");
        }
        return categoryOptional;
    }

    public List<Category> getCategoriesByProductId(Long productId) {
        //TODO implement
        return null;
    }
}
