package com.tresin.cvproj.handmade_shop.model;

import com.tresin.cvproj.handmade_shop.dto.AddressDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private User user;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "ZIP code is required")
    private String zipCode;

    // Constructors
    public Address(String street, String city, String zipCode) {
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }
    

    public void updateFromDTO(AddressDTO addressDTO) {
        this.setUser(addressDTO.getUser());
        this.setStreet(addressDTO.getStreet());
        this.setCity(addressDTO.getCity());
        this.setZipCode(addressDTO.getZipCode());
    }

    public AddressDTO toDTO() {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setUser(this.user);
        addressDTO.setStreet(this.street);
        addressDTO.setCity(this.city);
        addressDTO.setZipCode(this.zipCode);
        return addressDTO;
    }
}