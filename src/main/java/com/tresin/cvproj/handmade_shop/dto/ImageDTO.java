package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Image;
import com.tresin.cvproj.handmade_shop.model.Product;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ImageDTO {

    @NotBlank(message = "Product is required")
    private Product product;
    @NotBlank(message = "Url is required")
    private String url;

    /**
     * Converts the ImageDTO object to an Image object.
     *
     * @return The Image object converted from the ImageDTO.
     * @throws IllegalArgumentException If product or url is null.
     */
    public Image toImage() {
        if (this.product == null || this.url == null) {
            throw new IllegalArgumentException("Invalid ImageDTO: Product and url are required");
        }

        Image image = new Image();
        image.setProduct(this.getProduct());
        image.setUrl(this.getUrl());
        return image;
    }
}
