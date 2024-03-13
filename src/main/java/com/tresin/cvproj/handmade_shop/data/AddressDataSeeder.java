package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.repository.AddressRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@DependsOn("productDataSeeder")
public class AddressDataSeeder {

	private final AddressRepository addressRepository;
	private final boolean seedData;

	@Autowired
	public AddressDataSeeder(AddressRepository addressRepository, @Value("${app.seed.seed-data:false}") boolean seedData) {
		this.addressRepository = addressRepository;
		this.seedData = seedData;
	}

	@PostConstruct
	@Transactional
	public void seedAddresses() {
		if (seedData && addressRepository.count() == 0) {
			Address address1 = new Address("Testovacieho 11", "Košice", "040 01");
			Address address2 = new Address("Testovacieho 69", "Bratislava", "010 01");
			Address address3 = new Address("Testovacieho 420", "Košice", "040 02");

			addressRepository.saveAll(Arrays.asList(address1, address2, address3));
		}
	}
}
