package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Role;
import org.springboot.blog.agencyy.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    @Override
    public Role updateRole(Long id, Role roleDetails) {
        Role role = getRoleById(id);
        if (role != null) {
            role.setName(roleDetails.getName());
            return roleRepository.save(role);
        }
        return null;    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);

    }
}
