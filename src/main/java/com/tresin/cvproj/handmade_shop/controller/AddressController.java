package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.AddressApi;
import com.tresin.cvproj.handmade_shop.dto.AddressDTO;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AddressController implements AddressApi {

	private final AddressService addressService;

	@Autowired
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	@Override
	public ResponseEntity<Address> createAddress(@Valid @RequestBody AddressDTO addressDTO) {
		Address createdAddress = addressService.createAddress(addressDTO.toAddress());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
	}

	@Override
	public ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody AddressDTO addressDTO) {
		Address updatedAddress = addressDTO.toAddress();
		Address resultAddress = addressService.updateAddress(id, updatedAddress);
		return ResponseEntity.ok(resultAddress);
	}

	@Override
	public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
		addressService.deleteAddress(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Address>> getAllAddresses() {
		List<Address> addresses = addressService.getAllAddresses();
		return ResponseEntity.ok(addresses);
	}

	@Override
	public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
		return addressService.getAddressById(id)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}

	@Override
	public ResponseEntity<Address> getAddressByUserId(@PathVariable Long userId) {
		return addressService.getAddressByUserId(userId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
}
