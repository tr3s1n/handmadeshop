package com.tresin.cvproj.handmade_shop.data;

import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class RoleDataSeeder {
	
	private final RoleRepository roleRepository;
	private final boolean seedData;

	@Autowired
	public RoleDataSeeder(RoleRepository roleRepository, @Value("${app.seed.seed-data:false}") boolean seedData) {
		this.roleRepository = roleRepository;
		this.seedData = seedData;
	}

	@PostConstruct
	@Transactional
	public void seedRoles() {
		if (seedData && roleRepository.count() == 0) {
			Role role1 = new Role("SUPER_USER");
			Role role2 = new Role("ADMIN");
			Role role3 = new Role("USER");

			roleRepository.saveAll(Arrays.asList(role1, role2, role3));
		}
	}
}
