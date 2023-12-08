package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.AddressNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.repository.AddressRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService {

    private static final Logger logger = LoggerFactory.getLogger(AddressService.class);
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
    public Address createAddress(Address newAddress) {
        Address createdAddress = addressRepository.save(newAddress);
        logger.info("Address with ID {} created successfully", createdAddress.getId());
        return createdAddress;
    }

    public Address updateAddress(Long addressId, Address updatedAddress) {
        Optional<Address> existingAddressOptional = addressRepository.findById(addressId);
        if (existingAddressOptional.isPresent()) {
            Address existingAddress = existingAddressOptional.get();
            existingAddress.setUser(updatedAddress.getUser());
            existingAddress.setStreet(updatedAddress.getStreet());
            existingAddress.setCity(updatedAddress.getCity());
            existingAddress.setZipCode(updatedAddress.getZipCode());

            Address savedAddress = addressRepository.save(existingAddress);
            logger.info("Address with ID {} updated successfully", addressId);

            return savedAddress;
        } else {
            logger.warn("Address with ID {} not found in method updateAddress", addressId);
            throw new AddressNotFoundException("Address with ID " + addressId + " not found");
        }
    }

    public void deleteAddress(Long addressId) {
        Optional<Address> existingAddressOptional = addressRepository.findById(addressId);
        if (existingAddressOptional.isPresent()) {
            addressRepository.deleteById(addressId);
            logger.info("Address with ID {} deleted successfully", addressId);
        } else {
            logger.warn("Address with ID {} not found in method deleteAddress", addressId);
            throw new AddressNotFoundException("User with ID " + addressId + " not found");
        }
    }

    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    public Optional<Address> getAddressById(Long addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            logger.info("Address with ID {} found", addressId);
        } else {
            logger.warn("Address with ID {} not found in method getAddressById", addressId);
            throw new AddressNotFoundException("Address with ID " + addressId + " not found");
        }
        return addressOptional;
    }

}
