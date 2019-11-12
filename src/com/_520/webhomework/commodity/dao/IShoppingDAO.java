package com._520.webhomework.commodity.dao;

import com._520.webhomework.commodity.domain.Shopping;

import java.util.List;

public interface IShoppingDAO {

    void save(Shopping shopping);

    void delete(String name);

    void update(Shopping shopping,String name);

    Shopping getByName(String name);

    List<Shopping> listAll();

    void removeAll();
}
