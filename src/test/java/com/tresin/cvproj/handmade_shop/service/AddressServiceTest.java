package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.repository.AddressRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {

	@InjectMocks
	private AddressService addressService;

	@Mock
	private AddressRepository addressRepository;

	@Test
	public void testCreateAddress() {
		Address address = new Address("Testovacieho 11", "Košice", "040 01");
		when(addressRepository.save(any(Address.class))).thenReturn(address);
		Address createdAddress = addressService.createAddress(address);

		assertNotNull(createdAddress);
	}

	@Test
	public void testDeleteAddress() {
		long addressIdToDelete = 1L;
		Address addressToDelete = new Address("Testovacieho 11", "Košice", "040 01");
		when(addressRepository.findById(addressIdToDelete)).thenReturn(Optional.of(addressToDelete));
		addressService.deleteAddress(addressIdToDelete);

		verify(addressRepository, times(1)).deleteById(addressIdToDelete);
	}

	@Test
	public void testUpdateAddress() {
		long addressIdToUpdate = 1L;
		Address existingAddress = new Address("Testovacieho 11", "Košice", "040 01");
		Address updatedAddressData = new Address("Testovacieho 75", "Bratislava", "811 01");
		when(addressRepository.findById(addressIdToUpdate)).thenReturn(Optional.of(existingAddress));
		when(addressRepository.save(any(Address.class))).thenAnswer(invocation -> invocation.getArgument(0));
		Address updatedAddress = addressService.updateAddress(addressIdToUpdate, updatedAddressData);

		assertNotNull(updatedAddress);
		assertEquals(updatedAddressData.getStreet(), updatedAddress.getStreet());
		assertEquals(updatedAddressData.getCity(), updatedAddress.getCity());
		assertEquals(updatedAddressData.getCity(), updatedAddress.getCity());
		assertEquals(updatedAddressData.getZipCode(), updatedAddress.getZipCode());
	}

	@Test
	public void testGetAllAddresses() {
		List<Address> addressesList = Arrays.asList(
				new Address("Testovacieho 11", "Košice", "040 01"),
				new Address("Testovacieho 75", "Bratislava", "811 01")
		);
		when(addressRepository.findAll()).thenReturn(addressesList);
		List<Address> allAddresses = addressService.getAllAddresses();

		assertNotNull(allAddresses);
		assertEquals(addressesList.size(), allAddresses.size());
	}

	@Test
	public void testGetAddressById() {
		long addressIdToFind = 1L;
		Address foundAddress = new Address("Testovacieho 11", "Košice", "040 01");
		when(addressRepository.findById(addressIdToFind)).thenReturn(Optional.of(foundAddress));
		Optional<Address> addressById = addressService.getAddressById(addressIdToFind);

		assertTrue(addressById.isPresent());
		assertEquals(foundAddress, addressById.get());
	}
}
