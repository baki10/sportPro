package com.sportpro.service;

import com.sportpro.dao.UserDAO;
import com.sportpro.entity.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ilmir
 */
@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> getUsers() {
        return userDAO.getAll();
    }

    @Override
    @Transactional
    public void registerNewUser(User customer) {
        userDAO.create(customer);
    }

    @Override
    public User getUserByUsername(String username) {
        return userDAO.get(username);
    }

    @Override
    public boolean checkUserPassword(String name, String pwd) {
        User get = userDAO.get(name);
        if (get != null && get.getPassword().equals(pwd)) {
            return true;
        }
        return false;
    }

    @Override
    @Transactional
    public User updateUser(User customer) {
        return userDAO.update(customer);
    }
}
