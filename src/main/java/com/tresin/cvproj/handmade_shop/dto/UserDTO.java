package com.tresin.cvproj.handmade_shop.dto;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;
    @NotBlank(message = "First name is required")
    private String firstName;
    @NotBlank(message = "Last name is required")
    private String lastName;
    @NotBlank(message = "Address is required")
    private Address address;
    private List<Order> orders;
    private Set<Role> roles;

    /**
     * Converts the UserDTO object to a User object.
     *
     * @return The User object converted from the UserDTO.
     * @throws IllegalArgumentException If the username, email, firstName, lastName or address is null.
     */
    public User toUser() {
        if (this.username == null) {
            throw new IllegalArgumentException("Invalid UserDTO: Username, email, firstName, lastName and address are required");
        }
        if (this.orders == null) {
            this.orders = Collections.emptyList();
        }
        if (this.roles == null) {
            this.roles = Collections.emptySet();
        }

        User user = new User();
        user.setUsername(this.getUsername());
        user.setEmail(this.getEmail());
        user.setFirstName(this.getFirstName());
        user.setLastName(this.getLastName());
        user.setAddress(this.getAddress());
        user.setOrders(this.getOrders());
        user.setRoles(this.getRoles());
        return user;
    }
}
