package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.exception.AddressNotFoundException;
import com.tresin.cvproj.handmade_shop.exception.RoleNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Address;
import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.model.User;
import com.tresin.cvproj.handmade_shop.repository.UserRepository;
import com.tresin.cvproj.handmade_shop.service.AddressService;
import com.tresin.cvproj.handmade_shop.service.RoleService;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
@DependsOn("addressDataSeeder")
public class UserDataSeeder {

	private static final Logger logger = LoggerFactory.getLogger(UserDataSeeder.class);

	private final UserRepository userRepository;
	private final AddressService addressService;
	private final RoleService roleService;
	private final boolean seedData;

	@Autowired
	public UserDataSeeder(UserRepository userRepository, AddressService addressService, RoleService roleService, @Value("${app.seed.seed-data:false}") boolean seedData) {
		this.userRepository = userRepository;
		this.addressService = addressService;
		this.roleService = roleService;
		this.seedData = seedData;
	}

	@PostConstruct
	@Transactional
	public void seedUsers() {
		if (seedData && userRepository.count() == 0) {
			try {
				Address address1 = addressService.getAddressById(1L).orElseThrow(() -> new AddressNotFoundException("Address not found"));
				Address address2 = addressService.getAddressById(2L).orElseThrow(() -> new AddressNotFoundException("Address not found"));
				Address address3 = addressService.getAddressById(3L).orElseThrow(() -> new AddressNotFoundException("Address not found"));

				Role superRole = roleService.getRoleById(1L).orElseThrow(() -> new RoleNotFoundException("Role not found"));
				Role adminRole = roleService.getRoleById(2L).orElseThrow(() -> new RoleNotFoundException("Role not found"));
				Role userRole = roleService.getRoleById(3L).orElseThrow(() -> new RoleNotFoundException("Role not found"));

				Set<Role> roles1 = new HashSet<>(Collections.singletonList(superRole));
				Set<Role> roles2 = new HashSet<>(Collections.singletonList(adminRole));
				Set<Role> roles3 = new HashSet<>(Collections.singletonList(userRole));

				User user1 = new User("test_super", encodePassword("superpassword123"), "test1@gmail.com", address1, roles1);
				User user2 = new User("test_admin", encodePassword("adminpassword123"), "test2@gmail.com", address2, roles2);
				User user3 = new User("test_user", encodePassword("userpassword123"), "test3@gmail.com", address3, roles3);

				userRepository.saveAll(Arrays.asList(user1, user2, user3));
			} catch (Exception e) {
				logger.error("Error while seeding users: " + e.getMessage());
				throw new RuntimeException("Error while seeding users", e);
			}
		}
	}

	private String encodePassword(String rawPassword) {
		return new BCryptPasswordEncoder().encode(rawPassword);
	}

}
