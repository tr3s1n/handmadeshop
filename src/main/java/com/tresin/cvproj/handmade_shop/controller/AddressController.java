package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.dto.AddressDTO;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@PostMapping
	public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
		Address newAddress = new Address();
		newAddress.setUser(addressDTO.getUser());
		newAddress.setStreet(addressDTO.getStreet());
		newAddress.setCity(addressDTO.getCity());
		newAddress.setZipCode(addressDTO.getZipCode());
		Address createdAddress = addressService.createAddress(newAddress);

		return ResponseEntity.ok(createdAddress);
	}

	@PutMapping("/{addressId}")
	public ResponseEntity<Address> updateAddress(@PathVariable Long addressId, @Valid @RequestBody AddressDTO addressDTO) {
		Address updatedAddress = new Address();
		updatedAddress.setUser(addressDTO.getUser());
		updatedAddress.setStreet(addressDTO.getStreet());
		updatedAddress.setCity(addressDTO.getCity());
		updatedAddress.setZipCode(addressDTO.getZipCode());
		Address resultAddress = addressService.updateAddress(addressId, updatedAddress);

		if (resultAddress != null) {
			return ResponseEntity.ok(resultAddress);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{addressId}")
	public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
		addressService.deleteAddress(addressId);

		return ResponseEntity.noContent().build();
	}


	@GetMapping
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressService.getAllAddresses();

		return ResponseEntity.ok(addresses);
	}

	@GetMapping("/{addressId}")
	public ResponseEntity<Address> getAddressById(@PathVariable Long addressId) {
		return addressService.getAddressById(addressId).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

}
