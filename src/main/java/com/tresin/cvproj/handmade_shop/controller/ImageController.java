package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.ImageDTO;
import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.service.ImageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    @Autowired
    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping
    public ResponseEntity<Image> createImage(@Valid @RequestBody ImageDTO imageDTO) {
        Image newImage = new Image();
        newImage.setProduct(imageDTO.getProduct());
        newImage.setUrl(imageDTO.getUrl());
        Image createdImage = imageService.createImage(newImage);

        return ResponseEntity.ok(createdImage);
    }

    @PutMapping("/{imageId}")
    public ResponseEntity<Image> updateImage(@PathVariable Long imageId, @Valid @RequestBody ImageDTO imageDTO) {
        Image updatedImage = new Image();
        updatedImage.setProduct(imageDTO.getProduct());
        updatedImage.setUrl(imageDTO.getUrl());
        Image resultImage = imageService.updateImage(imageId, updatedImage);

        if (resultImage != null) {
            return ResponseEntity.ok(resultImage);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{imageId}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long imageId) {
        imageService.deleteImage(imageId);

        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Image>> getAllImages() {
        List<Image> images = imageService.getAllImages();

        return ResponseEntity.ok(images);
    }

    @GetMapping("/{imageId}")
    public ResponseEntity<Image> getImageById(@PathVariable Long imageId) {
        return imageService.getImageById(imageId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
