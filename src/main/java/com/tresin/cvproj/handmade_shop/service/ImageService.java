package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.ImageNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger(ImageService.class);
    private final ImageRepository imageRepository;

    @Autowired
    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image createImage(Image newImage) {
        Image createdImage = imageRepository.save(newImage);
        logger.info("Image with ID {} created successfully", createdImage.getId());
        return createdImage;
    }

    public Image updateImage(Long imageId, Image updatedImage) {
        Optional<Image> existingImageOptional = imageRepository.findById(imageId);
        if (existingImageOptional.isPresent()) {
            Image existingImage = existingImageOptional.get();
            existingImage.setProduct(updatedImage.getProduct());
            existingImage.setUrl(updatedImage.getUrl());

            Image savedImage = imageRepository.save(existingImage);
            logger.info("Image with ID {} updated successfully", imageId);

            return savedImage;
        } else {
            logger.warn("Image with ID {} not found in method updateImage", imageId);
            throw new ImageNotFoundException("Image with ID " + imageId + " not found");
        }
    }

    public void deleteImage(Long imageId) {
        Optional<Image> existingImageOptional = imageRepository.findById(imageId);
        if (existingImageOptional.isPresent()) {
            imageRepository.deleteById(imageId);
            logger.info("Image with ID {} deleted successfully", imageId);
        } else {
            logger.warn("Image with ID {} not found in method deleteImage", imageId);
            throw new ImageNotFoundException("Image with ID " + imageId + " not found");
        }
    }

    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    public Optional<Image> getImageById(Long imageId) {
        Optional<Image> imageOptional = imageRepository.findById(imageId);
        if (imageOptional.isPresent()) {
            logger.info("Image with ID {} found", imageId);
        } else {
            logger.warn("Image with ID {} not found in method getImageById", imageId);
            throw new ImageNotFoundException("Image with ID " + imageId + " not found");
        }
        return imageOptional;
    }

	public List<Image> getImagesByProductId(Long productId) {
        // TODO: implement
        return null;
	}
}
