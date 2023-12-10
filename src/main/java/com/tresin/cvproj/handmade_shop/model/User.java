package com.tresin.cvproj.handmade_shop.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "customer_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "user")
    private List<Order> orders;
    @OneToMany(mappedBy = "user")
    private List<Review> reviews;
    @OneToOne(mappedBy = "user")
    private Address address;
    @OneToOne(mappedBy = "user")
    private Cart cart;


    // Constructors
    public User() {

    }

    public User(String username, String password, String email, Address address) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
    }

    public User(String username, String password, String email, List<Order> orders, List<Review> reviews, Address address, Cart cart) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.orders = orders;
        this.reviews = reviews;
        this.address = address;
        this.cart = cart;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}