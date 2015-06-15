package com.sportpro.dao;

import com.sportpro.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends SuperDAOImpl<User, String> {

    public UserDAO() {
        super(User.class);
    }

}
