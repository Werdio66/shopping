package com._520.webhomework.commodity.dao;

import com._520.webhomework.commodity.domain.User;

public interface IUserDAO {


    void add(User user);

    User getByUsername(String username);
}
