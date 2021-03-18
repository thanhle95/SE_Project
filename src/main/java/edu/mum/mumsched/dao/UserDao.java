package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.Session;
import edu.mum.mumsched.domain.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    @Query("select u from User u where u.UserId= :id")
    public User findUserById(@Param("id") Long UserId);

    @Query("select u from User u where u.email= :email")
    public User findUserByEmail(@Param("email") String email);

    @Transactional
    @Modifying
    @Query("delete from User u where u.UserId= :id")
    public void removeById(@Param("id") Long UserId);

    @Query("select u from User u")
    public List<User> getAllUsers();
}
