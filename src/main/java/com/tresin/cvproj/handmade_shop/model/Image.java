package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.ImageDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Image {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Product is required")
	@ManyToOne
	private Product product;
	@NotBlank(message = "Url is required")
	private String url;


	public Image(Product product, String url) {
		this.product = product;
		this.url = url;
	}

	/**
	 * Updates the image entity based on the provided DTO.
	 *
	 * @param imageDTO The DTO containing the updated image information.
	 */
	public void updateFromDTO(ImageDTO imageDTO) {
		this.setProduct(imageDTO.getProduct());
		this.setUrl(imageDTO.getUrl());
	}

	/**
	 * Converts the image entity to a DTO.
	 *
	 * @return The DTO representation of the image entity.
	 */
	public ImageDTO toDTO() {
		ImageDTO imageDTO = new ImageDTO();
		imageDTO.setProduct(this.getProduct());
		imageDTO.setUrl(this.getUrl());
		return imageDTO;
	}
}
