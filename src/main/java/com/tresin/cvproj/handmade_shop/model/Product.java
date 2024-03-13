package com.tresin.cvproj.handmade_shop.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private double price;

    @ManyToOne
    private Cart cart;

    @OneToMany(mappedBy = "product")
    private List<Image> images;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    @OneToMany(mappedBy = "product")
    private List<Review> reviews;

    @ManyToMany
    @JoinTable(
            name = "product_category",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // Constructors
    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product(String name, double price, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.categories = categories;
    }

    public Product(String name, double price, Cart cart, List<Image> images, List<Order> orders, List<Review> reviews, List<Category> categories) {
        this.name = name;
        this.price = price;
        this.cart = cart;
        this.images = images;
        this.orders = orders;
        this.reviews = reviews;
        this.categories = categories;
    }
}
