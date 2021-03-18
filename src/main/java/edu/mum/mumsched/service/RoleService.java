package edu.mum.mumsched.service;

import edu.mum.mumsched.domain.Role;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {
    public void save(Role role);

    public Role getRoleByRoleID(long roleID);

    public Role getRoleByRoleName(String roleName);

    public List<Role> getAllRole();

    public void deleteRoleByRoleID(long roleID);
}
