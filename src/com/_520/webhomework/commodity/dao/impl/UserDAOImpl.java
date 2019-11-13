package com._520.webhomework.commodity.dao.impl;

import com._520.webhomework.commodity.dao.IUserDAO;
import com._520.webhomework.commodity.domain.User;
import com._520.webhomework.commodity.util.JdbcTemplete;

import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public void add(User user) {
        String sql = "INSERT INTO user (username,password) VALUES (?,?)";
        JdbcTemplete.update(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public User getByUsername(String username) {
        String sql = "SELECT username,password FROM user WHERE username = ?";
        List<User> users = JdbcTemplete.query(sql,(rs) ->{
           List<User> list = new ArrayList<>();
           while (rs.next()){
               User user = new User();
               user.setUsername(rs.getString("username"));
               user.setPassword(rs.getString("password"));
               list.add(user);
           }
           return list;
        },username);
        if (users == null){
            return null;
        }
        return users.size() == 1 ? users.get(0) : null;
    }
}
