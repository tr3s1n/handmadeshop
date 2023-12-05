package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.ProductNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product createProduct(Product newProduct) {
        return productRepository.save(newProduct);
    }

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

    public List<Product> getAllProducts() {
        logger.info("Fetching all products");
        return productRepository.findAll();
    }

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

}
