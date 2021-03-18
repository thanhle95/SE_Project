package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.UserDao;
import edu.mum.mumsched.domain.MiuUserDetails;
import edu.mum.mumsched.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import edu.mum.mumsched.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MiuUserDetailsServiceImp implements UserDetailsService, UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Could not find user with that email");
        }

        return new MiuUserDetails(user);
    }
    @Override
    public void save(User user){
        userDao.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public User getUserByUserID(long userID) {
        return userDao.findUserById(userID);
    }

    @Override
    public User getUserByEmail(String email) {
        return userDao.findUserByEmail(email);
    }

    @Override
    public void deleteUserByUserID(long userID) {
        userDao.removeById(userID);
    }
}