package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import com.tresin.cvproj.handmade_shop.service.AddressService;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
@DependsOn("addressDataSeeder") //Make sure that addresses are seeded before users as we are assigning address to user already while seeding.
public class UserDataSeeder {

	private static final Logger logger = LoggerFactory.getLogger(UserDataSeeder.class);
	private final UserRepository userRepository;
	private final AddressService addressService;

	@Autowired
	public UserDataSeeder(UserRepository userRepository, AddressService addressService) {
		this.userRepository = userRepository;
		this.addressService = addressService;
	}

	@PostConstruct
	public void seedUsers() {
		Optional<Address> optionalAddress1 = addressService.getAddressById(1L);
		Optional<Address> optionalAddress2 = addressService.getAddressById(2L);
		Optional<Address> optionalAddress3 = addressService.getAddressById(3L);

		if (optionalAddress1.isPresent() && optionalAddress2.isPresent() && optionalAddress3.isPresent()) {
			Address address1 = optionalAddress1.get();
			Address address2 = optionalAddress2.get();
			Address address3 = optionalAddress3.get();

			User user1 = new User("test1", "testpassword123.", "test1@gmail.com", address1);
			User user2 = new User("test2", "testpassword123.", "test2@gmail.com", address2);
			User user3 = new User("test3", "testpassword123.", "test3@gmail.com", address3);

			userRepository.saveAll(Arrays.asList(user1, user2, user3));
		} else {
			logger.warn("One or more of addresses was not present, thus no Product objects were seeded");
		}
	}
}
