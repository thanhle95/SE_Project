package edu.mum.mumsched.dao;

import edu.mum.mumsched.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends CrudRepository<User, Long> {
    @Query("select u from User u where u.UserId= :id")
    public User findUserById(@Param("id") Long UserId);

    @Query("select u from User u where u.email= :email")
    public User findUserByEmail(@Param("email") String email);

    @Query("delete from User u where u.UserId= :id")
    public void removeById(@Param("id") Long UserId);
}
