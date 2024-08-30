package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.RoleNotFoundException;
import com.tresin.cvproj.handmade_shop.exception.UserNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Cart;
import com.tresin.cvproj.handmade_shop.model.Order;
import com.tresin.cvproj.handmade_shop.model.Review;
import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.RoleRepository;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * The UserService class provides operations for managing users in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving users,
 * as well as managing their relationships with addresses, carts, orders, reviews, and roles.
 */
@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    /**
     * Creates a new user.
     *
     * @param newUser The user to be created.
     * @return The created user.
     */
    public User createUser(User newUser) {
        User createdUser = userRepository.save(newUser);
        logger.info("User with ID {} created successfully", createdUser.getId());
        return createdUser;
    }

    /**
     * Updates an existing user.
     *
     * @param userId      The ID of the user to update.
     * @param updatedUser The updated user information.
     * @return The updated user.
     * @throws UserNotFoundException If the user with the given ID is not found.
     */
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

    /**
     * Deletes an existing user.
     *
     * @param userId The ID of the user to delete.
     * @throws UserNotFoundException If the user with the given ID is not found.
     */
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

    /**
     * Retrieves all users.
     *
     * @return A list containing all users.
     */
    public List<User> getAllUsers() {
        logger.info("Fetching all users");
        return userRepository.findAll();
    }

    /**
     * Retrieves the user with the specified ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return An Optional containing the user with the given ID, if present.
     * @throws UserNotFoundException If the user with the given ID is not found.
     */
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

    /**
     * Updates the password of a user identified by their ID.
     *
     * @param userId      The ID of the user.
     * @param newPassword The new password for the user.
     * @throws UserNotFoundException If the user with the given ID is not found.
     */
    public void updateUserPassword(Long userId, String newPassword) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setPassword(newPassword);
            userRepository.save(existingUser);
            logger.info("User password with ID {} updated successfully", userId);
        } else {
            logger.warn("User with ID {} not found in method updateUserPassword", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    /**
     * Retrieves a list of orders associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of orders belonging to the user.
     */
    public List<Order> getOrdersByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getOrders)
                .orElse(Collections.emptyList());
    }

    /**
     * Retrieves a list of reviews associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A list of reviews belonging to the user.
     */
    public List<Review> getReviewsByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getReviews)
                .orElse(Collections.emptyList());
    }

    /**
     * Retrieves the address associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return An Optional containing the user's address, if found.
     */
    public Optional<Address> getAddressByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getAddress);
    }

    /**
     * Retrieves the cart associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return An Optional containing the user's cart, if found.
     */
    public Optional<Cart> getCartByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getCart);
    }

    /**
     * Adds a role to a user.
     *
     * @param userId The ID of the user.
     * @param roleId The ID of the role to add.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RoleNotFoundException If the role with the given ID is not found.
     */
    public void addRoleToUser(Long userId, Long roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Optional<Role> roleOptional = roleRepository.findById(roleId);
            if (roleOptional.isPresent()) {
                Role role = roleOptional.get();

                user.getRoles().add(role);
                userRepository.save(user);
                logger.info("Role with ID {} added to user with ID {}", roleId, userId);
            } else {
                logger.warn("Role with ID {} not found in method addRoleToUser", roleId);
                throw new RoleNotFoundException("Role with ID " + roleId + " not found");
            }
        } else {
            logger.warn("User with ID {} not found in method addRoleToUser", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    /**
     * Removes a role from a user.
     *
     * @param userId The ID of the user.
     * @param roleId The ID of the role to remove.
     * @throws UserNotFoundException If the user with the given ID is not found.
     * @throws RoleNotFoundException If the role with the given ID is not found.
     */
    public void removeRoleFromUser(Long userId, Long roleId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            Optional<Role> roleOptional = roleRepository.findById(roleId);
            if (roleOptional.isPresent()) {
                Role role = roleOptional.get();

                user.getRoles().remove(role);
                userRepository.save(user);
                logger.info("Role with ID {} removed from user with ID {}", roleId, userId);
            } else {
                logger.warn("Role with ID {} not found in method removeRoleFromUser", roleId);
                throw new RoleNotFoundException("Role with ID " + roleId + " not found");
            }
        } else {
            logger.warn("User with ID {} not found in method removeRoleFromUser", userId);
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }
    }

    /**
     * Retrieves a set of roles associated with a specific user.
     *
     * @param userId The ID of the user.
     * @return A set of roles belonging to the user.
     */
    public Set<Role> getRolesByUserId(Long userId) {
        return userRepository.findById(userId)
                .map(User::getRoles)
                .orElse(Collections.emptySet());
    }

    /**
     * Retrieves a user by their username.
     *
     * @param username The username of the user.
     * @return An Optional containing the user, if found.
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findOptionalByUsername(username);
    }

    /**
     * Retrieves a user by their email address.
     *
     * @param email The email address of the user.
     * @return An Optional containing the user, if found.
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Deletes all reviews associated with a specific user.
     *
     * @param userId The ID of the user.
     */
    public void deleteUserReviews(Long userId) {
        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.getReviews().clear();
                    userRepository.save(user);
                    logger.info("Deleted all reviews associated with user ID {}", userId);
                });
    }

    /**
     * Deletes all orders associated with a specific user.
     *
     * @param userId The ID of the user.
     */
    public void deleteUserOrders(Long userId) {
        userRepository.findById(userId)
                .ifPresent(user -> {
                    user.getOrders().clear();
                    userRepository.save(user);
                    logger.info("Deleted all orders associated with user ID {}", userId);
                });
    }
}
