package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.UserNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Product;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User newUser) {
        User createdUser = userRepository.save(newUser);
        logger.info("User with ID {} created successfully", createdUser.getId());
        return createdUser;
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setPassword(updatedUser.getPassword());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setAddress(updatedUser.getAddress());

            User savedUser = userRepository.save(existingUser);
            logger.info("User with ID {} updated successfully", userId);

            return savedUser;
        } else {
            logger.warn("User with ID {} not found in method updateUser", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    public void deleteUser(Long userId) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            userRepository.deleteById(userId);
            logger.info("User with ID {} deleted successfully", userId);
        } else {
            logger.warn("User with ID {} not found in method deleteUser", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            logger.info("User with ID {} found", userId);
        } else {
            logger.warn("User with ID {} not found in method getUserById", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
        return userOptional;
    }

    // TODO: implement
	public Void updateUserPassword(Long id, String newPassword) {
        return null;
	}

    public List<Order> getOrdersByUserId(Long id) {
        return null;
    }

    public List<Review> getReviewsByUserId(Long id) {
        return null;
    }

    public Optional<Address> getAddressByUserId(Long id) {
        return null;
    }

    public Optional<Cart> getCartByUserId(Long id) {
        return null;
    }

    public Void addRoleToUser(Long userId, Long roleId) {
        return null;
    }

    public Void removeRolFromUser(Long userId, Long roleId) {
        return null;
    }

    public Set<Role> getRolesByUserId(Long id) {
        return null;
    }

    public Optional<User> getUserByUsername(String username) {
        return null;
    }

    public Optional<User> getUserByEmail(String email) {
        return null;
    }

    public Void deleteUserReviews(Long id) {
        return null;
    }

    public Void deleteUserOrders(Long id) {
        return null;
    }
}
