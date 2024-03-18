package com.tresin.cvproj.handmade_shop.controller;

import com.tresin.cvproj.handmade_shop.api.RoleApi;
import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoleController implements RoleApi {

	private final RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@Override
	public ResponseEntity<Role> createRole(@RequestBody Role role) {
		Role createdRole = roleService.createRole(role);
		return ResponseEntity.ok(createdRole);
	}

	@Override
	public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody Role role) {
		Role updatedRole = roleService.updateRole(id, role);
		if (updatedRole != null) {
			return ResponseEntity.ok(updatedRole);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@Override
	public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
		roleService.deleteRole(id);
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<List<Role>> getAllRoles() {
		List<Role> roles = roleService.getAllRoles();
		return ResponseEntity.ok(roles);
	}

	@Override
	public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
		return roleService.getRoleById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}
}
