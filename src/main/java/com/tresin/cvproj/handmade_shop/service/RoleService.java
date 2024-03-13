package com.tresin.cvproj.handmade_shop.service;

import com.tresin.cvproj.handmade_shop.exception.RoleNotFoundException;
import com.tresin.cvproj.handmade_shop.model.Role;
import com.tresin.cvproj.handmade_shop.repository.RoleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role createRole(Role newRole) {
        Role createdRole = roleRepository.save(newRole);
        logger.info("Role with ID {} created successfully", createdRole.getId());
        return createdRole;
    }

    public Role updateRole(Long roleId, Role updatedRole) {
        Optional<Role> existingRoleOptional = roleRepository.findById(roleId);
        if (existingRoleOptional.isPresent()) {
            Role existingRole = existingRoleOptional.get();
            existingRole.setName(updatedRole.getName());

            Role savedRole = roleRepository.save(existingRole);
            logger.info("Role with ID {} updated successfully", roleId);

            return savedRole;
        } else {
            logger.warn("Role with ID {} not found in method updateRole", roleId);
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
    }

    public void deleteRole(Long roleId) {
        Optional<Role> existingRoleOptional = roleRepository.findById(roleId);
        if (existingRoleOptional.isPresent()) {
            roleRepository.deleteById(roleId);
            logger.info("Role with ID {} deleted successfully", roleId);
        } else {
            logger.warn("Role with ID {} not found in method deleteRole", roleId);
            throw new RoleNotFoundException("User with ID " + roleId + " not found");
        }
    }

    public List<Role> getAllRoles() {
        logger.info("Fetching all roles");
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long roleId) {
        Optional<Role> roleOptional = roleRepository.findById(roleId);
        if (roleOptional.isPresent()) {
            logger.info("Role with ID {} found", roleId);
        } else {
            logger.warn("Role with ID {} not found in method getRoleById", roleId);
            throw new RoleNotFoundException("Role with ID " + roleId + " not found");
        }
        return roleOptional;
    }

}
