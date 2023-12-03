package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.UserNotFoundException;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(userId);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());

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

}
