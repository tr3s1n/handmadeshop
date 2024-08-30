package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.ImageNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.repository.ImageRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The ImageService class provides operations for managing images in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving images.
 */
@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    /**
     * Creates a new image.
     *
     * @param newImage The image to be created.
     * @return The created image.
     * @throws IllegalArgumentException      If the newImage is null.
     * @throws ConstraintViolationException  If the newImage violates constraints specified by annotations in the Image model class.
     */
    public Image createImage(Image newImage) {
        if (newImage == null) {
            throw new IllegalArgumentException("Image cannot be null");
        }

        Image createdImage = imageRepository.save(newImage);
        logger.info("Image created successfully: {}", createdImage);
        return createdImage;
    }

    /**
     * Updates an existing image.
     *
     * @param id           The ID of the image to update.
     * @param updatedImage The updated image information.
     * @return The updated image.
     * @throws IllegalArgumentException     If id or updatedImage is null or if id is invalid.
     * @throws ImageNotFoundException       If the image with the given ID is not found.
     * @throws ConstraintViolationException If the updatedImage violates constraints specified by annotations in the Image model class.
     */
    public Image updateImage(Long id, Image updatedImage) {
        if (id == null || id <= 0 || updatedImage == null) {
            throw new IllegalArgumentException("Invalid image or ID");
        }

        return imageRepository.findById(id)
                .map(existingImage -> {
                    existingImage.updateFromDTO(updatedImage.toDTO());
                    Image savedImage = imageRepository.save(existingImage);
                    logger.info("Image with ID {} updated successfully", id);
                    return savedImage;
                })
                .orElseThrow(() -> {
                    logger.error("Image with ID {} not found in method updateImage", id);
                    return new ImageNotFoundException("Image with ID " + id + " not found");
                });
    }

    /**
     * Deletes an existing image.
     *
     * @param id The ID of the image to delete.
     * @throws IllegalArgumentException  If the ID is null or invalid.
     * @throws ImageNotFoundException    If the image with the given ID is not found.
     */
    public void deleteImage(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid image ID");
        }

        Optional<Image> imageOptional = imageRepository.findById(id);
        imageOptional.ifPresentOrElse(
                image -> {
                    imageRepository.deleteById(id);
                    logger.info("Image with ID {} deleted successfully", id);
                },
                () -> {
                    logger.error("Image with ID {} not found in method deleteImage", id);
                    throw new ImageNotFoundException("Image with ID " + id + " not found");
                }
        );
    }

    /**
     * Retrieves all images.
     *
     * @return A list containing all images.
     */
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    /**
     * Retrieves the image with the specified ID.
     *
     * @param id The ID of the image to retrieve.
     * @return An Optional containing the image with the given ID, if present.
     * @throws IllegalArgumentException If the ID is null or invalid.
     * @throws ImageNotFoundException   If the image with the given ID is not found.
     */
    public Optional<Image> getImageById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid image ID");
        }

        Optional<Image> imageOptional = imageRepository.findById(id);
        imageOptional.ifPresentOrElse(
                image -> logger.info("Image with ID {} found", id),
                () -> {
                    logger.error("Image with ID {} not found in method getImageById", id);
                    throw new ImageNotFoundException("Image with ID " + id + " not found");
                });
        return imageOptional;
    }

    /**
     * Retrieves the list of images associated with the specified product ID.
     *
     * @param productId The ID of the product.
     * @return A list of images associated with the product.
     * @throws IllegalArgumentException   If the product ID is null or invalid.
     * @throws ImageNotFoundException     If no images are found for the specified product ID.
     */
    public List<Image> getImagesByProductId(Long productId) {
        if (productId == null || productId <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }

        List<Image> images = imageRepository.findImagesByProduct_Id(productId);
        if (images.isEmpty()) {
            throw new ImageNotFoundException("No images found for product ID: " + productId);
        }
        return images;
    }
}
