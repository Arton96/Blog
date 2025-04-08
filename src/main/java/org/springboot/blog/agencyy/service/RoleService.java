package org.springboot.blog.agencyy.service;

import org.springboot.blog.agencyy.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleById(Long id);
    Role createRole(Role role);
    Role updateRole(Long id, Role roleDetails);
    void deleteRole(Long id);
}
