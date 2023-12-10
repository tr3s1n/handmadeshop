package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.repository.AddressRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@DependsOn("productDataSeeder")
public class AddressDataSeeder {

	private final AddressRepository addressRepository;

	@Autowired
	public AddressDataSeeder(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@PostConstruct
	public void seedCategories() {
		Address address1 = new Address("Testovacieho 11", "Košice", "04001");
		Address address2 = new Address("Testovacieho 69", "Bratislava", "01001");
		Address address3 = new Address("Testovacieho 420", "Košice", "04002");

		addressRepository.saveAll(Arrays.asList(address1, address2, address3));
	}
}
