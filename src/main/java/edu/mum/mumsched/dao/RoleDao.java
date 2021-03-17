package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Entry;
import edu.mum.mumsched.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao extends CrudRepository<Role, Long> {
    @Query("select r from Role r where r.roleId= :id")
    public Role findRoleById(@Param("id") Long roleId);

    @Query("select r from Role r where r.name= :name")
    public Role findRoleByName(@Param("name") String name);

    @Query("select r from Role r")
    public List<Role> getAllRole();

    @Query("delete from Role r where r.roleId= :id")
    public void removeById(@Param("id") Long roleId);
}