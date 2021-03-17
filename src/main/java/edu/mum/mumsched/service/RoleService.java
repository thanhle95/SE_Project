package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Role;

import java.util.List;

public interface RoleService {
    public void save(Role role);

    public Role getRoleByRoleID(long roleID);

    public Role getRoleByRoleName(String roleName);

    public List<Role> getAllRole();

    public void deleteRoleByRoleID(long roleID);
}
