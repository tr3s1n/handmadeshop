package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.AddressNotFoundException;
import com.tresin.cvproj.handmade_shop.exception.UserNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.AddressRepository;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The AddressService class provides operations for managing addresses in the system.
 * It encapsulates the business logic for creating, updating, deleting, and retrieving addresses.
 */
@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;
    private final UserService userService;

    @Autowired
    public AddressService(AddressRepository addressRepository, UserService userService) {
        this.addressRepository = addressRepository;
        this.userService = userService;
    }

    /**
     * Creates a new address.
     *
     * @param newAddress The address to be created.
     * @return The created address.
     * @throws IllegalArgumentException      If the newAddress is null.
     * @throws ConstraintViolationException  If the updatedAddress violates constraints specified by annotations in the Address model class.
     */
    public Address createAddress(Address newAddress) {
        if (newAddress == null) {
            throw new IllegalArgumentException("Address cannot be null");
        }

        Address createdAddress = addressRepository.save(newAddress);
        logger.info("Address created successfully: {}", createdAddress);
        return createdAddress;
    }

    /**
     * Updates an existing address.
     *
     * @param id                 The ID of the address to update.
     * @param updatedAddress     The updated address information.
     * @return The updated address.
     * @throws IllegalArgumentException      If id or updatedAddress is null.
     * @throws AddressNotFoundException      If the address with the given ID is not found.
     * @throws ConstraintViolationException  If the updatedAddress violates constraints specified by annotations in the Address model class.
     */
    public Address updateAddress(Long id, Address updatedAddress) {
        if (id == null || updatedAddress == null) {
            throw new IllegalArgumentException("ID and Address cannot be null");
        }

        return addressRepository.findById(id)
                .map(existingAddress -> {
                    existingAddress.updateFromDTO(updatedAddress.toDTO());
                    Address savedAddress = addressRepository.save(existingAddress);
                    logger.info("Address with ID {} updated successfully", id);
                    return savedAddress;
                })
                .orElseThrow(() -> {
                    logger.error("Address with ID {} not found in method updateAddress", id);
                    return new AddressNotFoundException("Address with ID " + id + " not found");
                });
    }

    /**
     * Deletes the address with the specified ID.
     *
     * @param id The ID of the address to delete.
     * @throws IllegalArgumentException    If the ID is null.
     * @throws AddressNotFoundException    If the address with the given ID is not found.
     */
    public void deleteAddress(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Optional<Address> addressOptional = addressRepository.findById(id);
        addressOptional.ifPresentOrElse(
                address -> {
                    addressRepository.deleteById(id);
                    logger.info("Address with ID {} deleted successfully", id);
                },
                () -> {
                    logger.error("Address with ID {} not found in method deleteAddress", id);
                    throw new AddressNotFoundException("Address with ID " + id + " not found");
                }
        );
    }

    /**
     * Retrieves all addresses.
     *
     * @return A list containing all addresses.
     */
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    /**
     * Retrieves the address with the specified ID.
     *
     * @param id The ID of the address to retrieve.
     * @return An Optional containing the address with the given ID, if present.
     * @throws IllegalArgumentException    If the ID is null.
     * @throws AddressNotFoundException    If the address with the given ID is not found.
     */
    public Optional<Address> getAddressById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID cannot be null");
        }

        Optional<Address> addressOptional = addressRepository.findById(id);
        addressOptional.ifPresentOrElse(
                address -> logger.info("Address with ID {} found", id),
                () -> {
                    logger.error("Address with ID {} not found in method getAddressById", id);
                    throw new AddressNotFoundException("Address with ID " + id + " not found");
                }
        );
        return addressOptional;
    }

    /**
     * Retrieves the address associated with the specified user ID.
     *
     * @param userId The ID of the user whose address is to be retrieved.
     * @return An Optional containing the address associated with the given user ID, if present.
     * @throws IllegalArgumentException    If the userID is null or invalid
     * @throws UserNotFoundException       If the user for the given user ID is not found.
     * @throws AddressNotFoundException    If the address for the given user ID is not found.
     */
    public Optional<Address> getAddressByUserId(Long userId) {
        if (userId == null || userId <= 0) {
            throw new IllegalArgumentException("Invalid user ID");
        }

        Optional<User> userOptional = userService.getUserById(userId);
        if (userOptional.isEmpty()) {
            throw new UserNotFoundException("User with ID " + userId + " not found");
        }

        Optional<Address> addressOptional = addressRepository.findByUserId(userId);
        addressOptional.ifPresentOrElse(
                address -> logger.info("Address found for user ID: {}", userId),
                () -> {
                    logger.error("Address not found for user ID: {}", userId);
                    throw new AddressNotFoundException("Address not found for user ID: " + userId);
        });
        return addressOptional;
    }
}
