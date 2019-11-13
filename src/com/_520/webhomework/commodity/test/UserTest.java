package com._520.webhomework.commodity.test;

import com._520.webhomework.commodity.dao.IUserDAO;
import com._520.webhomework.commodity.dao.impl.UserDAOImpl;
import com._520.webhomework.commodity.domain.User;
import org.junit.jupiter.api.Test;

public class UserTest {
    private static IUserDAO userDAO;

    @Test
    void add(){
        userDAO = new UserDAOImpl();
        User user = new User();
        user.setUsername("root");
        user.setPassword("admin");
        userDAO.add(user);
    }
    @Test
    void get(){
        userDAO = new UserDAOImpl();
        System.out.println(userDAO.getByUsername("root"));
    }
}
