package com.sportpro.service;
import com.sportpro.entity.User;
import java.util.List;

/**
 *
 * @author ilmir
 */
public interface UserService {

    User getUserByUsername(String username);

    List<User> getUsers();

    void registerNewUser(User customer);
    
    boolean checkUserPassword(String name, String pwd);

    User updateUser(User customer);
    
}
