package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.User;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDTO {

    @NotBlank(message = "User is required")
    private User user;
    @NotBlank(message = "Street is required")
    private String street;
    @NotBlank(message = "City is required")
    private String city;
    @NotBlank(message = "ZIP code is required")
    private String zipCode;

    public Address toAddress() {
        if (this.user == null || this.street == null || this.city == null || this.zipCode == null) {
            throw new IllegalArgumentException("Invalid AddressDTO: User, Street, City, and ZIP code are required");
        }

        Address address = new Address();
        address.setUser(this.user);
        address.setStreet(this.street);
        address.setCity(this.city);
        address.setZipCode(this.zipCode);
        return address;
    }
}
