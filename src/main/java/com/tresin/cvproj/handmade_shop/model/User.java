package com.tresin.cvproj.handmade_shop.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "user")
    private List<Order> orders;

    @OneToMany(mappedBy = "user")
    private List<Review> reviews;

    @OneToOne(mappedBy = "user")
    private Address address;

    @OneToOne(mappedBy = "user")
    private Cart cart;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles = new HashSet<>();

    // Builder for Constructor
    @Builder
    public User(String username, String password, String email, Address address, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.roles = (roles != null) ? roles : new HashSet<>();
    }

}
