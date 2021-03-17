package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.RoleDao;
import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.domain.Role;
import edu.mum.mumsched.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleServiceImp implements RoleService {

    @Autowired
    RoleDao roleDAO;

    public void save(Role role){
        roleDAO.save(role);
    }

    @Override
    public Role getRoleByRoleID(long roleID) {
        return roleDAO.findRoleById(roleID);
    }

    @Override
    public Role getRoleByRoleName(String roleName) {
        return roleDAO.findRoleByName(roleName);
    }

    @Override
    public void deleteRoleByRoleID(long roleID) {
        roleDAO.removeById(roleID);
    }

    @Override
    public List<Role> getAllRole() {
        return roleDAO.getAllRole();
    }
}
