package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.CartNotFoundException;
import com.tresin.cvproj.handmade_shop.exception.UserNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.CartRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * The AddressService class provides operations for managing carts in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving carts.
 */
@Service
public class CartService {
    
    private static final Logger logger = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    private final UserService userService;

    @Autowired
    public CartService(CartRepository cartRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
    }

    /**
     * Creates a new cart.
     *
     * @param newCart The cart to be created.
     * @return The created cart.
     * @throws IllegalArgumentException      If the newCart is null.
     * @throws ConstraintViolationException  If the newCart violates constraints specified by annotations in the Cart model class.
     */
    public Cart createCart(Cart newCart) {
        if (newCart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }

        Cart createdCart = cartRepository.save(newCart);
        logger.info("Cart created successfully: {}", createdCart);
        return createdCart;
    }

    /**
     * Updates an existing cart.
     *
     * @param id          The ID of the cart to update.
     * @param updatedCart The updated cart information.
     * @return The updated cart.
     * @throws IllegalArgumentException     If id or updatedCart is null or if id is invalid.
     * @throws CartNotFoundException        If the cart with the given ID is not found.
     * @throws ConstraintViolationException If the updatedCart violates constraints specified by annotations in the Cart model class.
     */
    public Cart updateCart(Long id, Cart updatedCart) {
        if (id == null || id <= 0 || updatedCart == null) {
            throw new IllegalArgumentException("Invalid cart or ID");
        }

        return cartRepository.findById(id)
                .map(existingCart -> {
                    existingCart.updateFromDTO(updatedCart.toDTO());
                    Cart savedCart = cartRepository.save(existingCart);
                    logger.info("Cart with ID {} updated successfully", id);
                    return savedCart;
                })
                .orElseThrow(() -> {
                    logger.error("Cart with ID {} not found in method updateCart", id);
                    return new CartNotFoundException("Cart with ID " + id + " not found");
                });
    }

    /**
     * Deletes an existing cart.
     *
     * @param id The ID of the cart to delete.
     * @throws IllegalArgumentException  If the ID is null or invalid.
     * @throws CartNotFoundException     If the cart with the given ID is not found.
     */
    public void deleteCart(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid cart ID");
        }

        Optional<Cart> cartOptional = cartRepository.findById(id);
        cartOptional.ifPresentOrElse(
                cart -> {
                    cartRepository.deleteById(id);
                    logger.info("Cart with ID {} deleted successfully", id);
                },
                () -> {
                    logger.error("Cart with ID {} not found in method deleteCart", id);
                    throw new CartNotFoundException("Cart with ID " + id + " not found");
                }
        );
    }

    /**
     * Retrieves all carts.
     *
     * @return A list containing all carts.
     */
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    /**
     * Retrieves the cart with the specified ID.
     *
     * @param id The ID of the cart to retrieve.
     * @return An Optional containing the cart with the given ID, if present.
     * @throws IllegalArgumentException If the ID is null or invalid.
     * @throws CartNotFoundException    If the cart with the given ID is not found.
     */
    public Optional<Cart> getCartById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid cart ID");
        }

        Optional<Cart> cartOptional = cartRepository.findById(id);
        cartOptional.ifPresentOrElse(
                cart -> logger.info("Cart with ID {} found", id),
                () -> {
                    logger.error("Cart with ID {} not found in method getCartById", id);
                    throw new CartNotFoundException("Cart with ID " + id + " not found");
                }
        );
        return cartOptional;
    }

    /**
     * Retrieves the cart associated with the specified user ID.
     *
     * @param userId The ID of the user whose cart is to be retrieved.
     * @return An Optional containing the cart associated with the given user ID, if present.
     * @throws IllegalArgumentException If the userID is null or invalid.
     * @throws UserNotFoundException    If the user for the given user ID is not found.
     * @throws CartNotFoundException    If the cart for the given user ID is not found.
     */
    public Optional<Cart> getCartByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }

        Optional<Cart> cartOptional = cartRepository.findByUserId(userId);
        cartOptional.ifPresentOrElse(
                cart -> logger.info("Cart found for user ID: {}", userId),
                () -> {
                    logger.error("Cart not found for user ID: {}", userId);
                    throw new CartNotFoundException("Cart not found for user ID: " + userId);
                });
        return cartOptional;
    }

    /**
     * Retrieves the list of products associated with the specified cart ID.
     *
     * @param id The ID of the cart.
     * @return A list of products associated with the cart, or an empty list if no products are found.
     * @throws IllegalArgumentException If the cart ID is null.
     * @throws CartNotFoundException    If the cart with the given ID is not found.
     */
    public List<Product> getCartProducts(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("Invalid cart ID");
        }

        return cartRepository.findById(id)
                .map(Cart::getProducts)
                .orElseThrow(() -> {
                    logger.error("Cart with ID {} not found in method getCartProducts", id);
                    return new CartNotFoundException("Cart with ID " + id + " not found");
                });
    }
}
