package edu.mum.mumsched.service;


import edu.mum.mumsched.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    public void save(User user);

    public User getUserByUserID(long userID);

    public User getUserByEmail(String email);

    public List<User> getAllUsers();

    public void deleteUserByUserID(long userID);
}
