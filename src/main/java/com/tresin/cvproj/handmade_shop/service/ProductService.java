package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.ProductNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.repository.ProductRepository;
import com.tresin.cvproj.handmade_shop.specification.ProductSpecification;
import com.tresin.cvproj.handmade_shop.specification.SearchCriteria;
import com.tresin.cvproj.handmade_shop.specification.SearchOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * The ProductService class provides operations for managing products in the system.
 * It encapsulates the business logic for creating, updating, deleting, retrieving, sorting, and searching products.
 */
@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Creates a new product.
     *
     * @param newProduct The product to be created.
     * @return The created product.
     */
    public Product createProduct(Product newProduct) {
        Product createdProduct = productRepository.save(newProduct);
        logger.info("Product with ID {} created successfully", createdProduct.getId());
        return createdProduct;
    }

    /**
     * Updates an existing product.
     *
     * @param productId      The ID of the product to update.
     * @param updatedProduct The updated product information.
     * @return The updated product.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
    public Product updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);

        if (existingProductOptional.isPresent()) {
            Product existingProduct = existingProductOptional.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setPrice(updatedProduct.getPrice());
            existingProduct.setCategories(updatedProduct.getCategories());
            existingProduct.setImages(updatedProduct.getImages());

            Product savedProduct = productRepository.save(existingProduct);
            logger.info("Product with ID {} updated successfully", productId);

            return savedProduct;
        } else {
            logger.warn("Product with ID {} not found in method updateProduct", productId);
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }
    }

    /**
     * Deletes an existing product.
     *
     * @param productId The ID of the product to delete.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
    public void deleteProduct(Long productId) {
        Optional<Product> existingProductOptional = productRepository.findById(productId);
        if (existingProductOptional.isPresent()) {
            productRepository.deleteById(productId);
            logger.info("Product with ID {} deleted successfully", productId);
        } else {
            logger.warn("Product with ID {} not found in method deleteProduct", productId);
            throw new ProductNotFoundException("User with ID " + productId + " not found");
        }
    }

    /**
     * Retrieves all products.
     *
     * @return A list containing all products.
     */
    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }

    /**
     * Retrieves the product with the specified ID.
     *
     * @param productId The ID of the product to retrieve.
     * @return An Optional containing the product with the given ID, if present.
     * @throws ProductNotFoundException If the product with the given ID is not found.
     */
    public Optional<Product> getProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        if (productOptional.isPresent()) {
            logger.info("Product with ID {} found", productId);
        } else {
            logger.warn("Product with ID {} not found in method getProductById", productId);
            throw new ProductNotFoundException("Product with ID " + productId + " not found");
        }

        return productOptional;
    }

    /**
     * Sorts the list of products based on the provided sort criteria.
     *
     * @param sortBy The criteria to sort by (e.g., "name", "price").
     * @return The sorted list of products.
     * @throws IllegalArgumentException If the provided sort criteria is invalid.
     */
    public List<Product> sortProducts(String sortBy) {
        List<Product> products = productRepository.findAll();

        try {
            if (sortBy != null && !sortBy.isEmpty()) {
                switch (sortBy) {
                    case "name":
                        products.sort(Comparator.comparing(Product::getName));
                        break;
                    case "price":
                        products.sort(Comparator.comparingDouble(Product::getPrice));
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid sort criteria: " + sortBy);
                }
            }
        } catch (IllegalArgumentException e) {
            logger.warn("Invalid sort criteria: {}", e.getMessage());
        }

        // Default sorting by name
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }

    /**
     * Retrieves products by category ID.
     *
     * @param categoryId The ID of the category.
     * @return A list of products belonging to the specified category.
     */
    public List<Product> getProductsByCategoryId(Long categoryId) {
        return productRepository.findByCategoryId(categoryId);
    }

    /**
     * Retrieves a product by image ID.
     *
     * @param imageId The ID of the image.
     * @return The product associated with the image, if found.
     */
    public Optional<Product> getProductByImageId(Long imageId) {
        return productRepository.findByImageId(imageId);
    }

    /**
     * Retrieves a product by review ID.
     *
     * @param reviewId The ID of the review.
     * @return The product associated with the review, if found.
     */
    public Optional<Product> getProductByReviewId(Long reviewId) {
        return productRepository.findByReviewId(reviewId);
    }

    /**
     * Retrieves products by cart ID.
     *
     * @param cartId The ID of the cart.
     * @return A list of products associated with the cart.
     */
    public List<Product> getProductsByCartId(Long cartId) {
        return productRepository.findByCartId(cartId);
    }

    /**
     * Retrieves products by order ID.
     *
     * @param orderId The ID of the order.
     * @return A list of products associated with the order.
     */
    public List<Product> getProductsByOrderId(Long orderId) {
        return productRepository.findByOrderId(orderId);
    }

    /**
     * Searches for products based on keywords, category, minimum price, and maximum price.
     *
     * @param keyword  The keyword to search for.
     * @param category The category to filter by.
     * @param minPrice The minimum price.
     * @param maxPrice The maximum price.
     * @return A list of products matching the search criteria.
     */
    public List<Product> searchProducts(String keyword, String category, Double minPrice, Double maxPrice) {
        ProductSpecification productSpecification = new ProductSpecification();

        if (keyword != null && !keyword.isEmpty()) {
            productSpecification.addCriteria(new SearchCriteria("name", SearchOperation.CONTAINS, keyword));
        }
        if (category != null && !category.isEmpty()) {
            productSpecification.addCriteria(new SearchCriteria("categories.name", SearchOperation.EQUAL, category));
        }
        if (minPrice != null) {
            productSpecification.addCriteria(new SearchCriteria("price", SearchOperation.GREATER_THAN_EQUAL, minPrice));
        }
        if (maxPrice != null) {
            productSpecification.addCriteria(new SearchCriteria("price", SearchOperation.LESS_THAN_EQUAL, maxPrice));
        }

        return productRepository.findAllBySpecification(productSpecification);
    }

    /**
     * Filters products based on the provided category and color.
     *
     * @param category The category to filter by (optional).
     * @param color    The color to filter by (optional).
     * @return A list of products that match the specified category and color criteria.
     */
    public List<Product> filterProducts(String category, String color) {
        ProductSpecification productSpecification = new ProductSpecification();

        if (category != null && !category.isEmpty()) {
            productSpecification.addCriteria(new SearchCriteria("categories.name", SearchOperation.EQUAL, category));
        }
        if (color != null && !color.isEmpty()) {
            productSpecification.addCriteria(new SearchCriteria("color", SearchOperation.EQUAL, color));
        }

        return productRepository.findAllBySpecification(productSpecification);
    }
}
