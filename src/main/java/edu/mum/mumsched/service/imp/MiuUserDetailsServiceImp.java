package edu.mum.mumsched.service.imp;

import edu.mum.mumsched.dao.UserDao;
import edu.mum.mumsched.domain.MiuUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import edu.mum.mumsched.domain.User;

public class MiuUserDetailsServiceImp implements UserDetailsService {

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

    public void save(User user){
        userDao.save(user);
    }
}