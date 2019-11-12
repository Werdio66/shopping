package com._520.webhomework.commodity.dao.impl;

import com._520.webhomework.commodity.dao.IShoppingDAO;
import com._520.webhomework.commodity.domain.Shopping;
import com._520.webhomework.commodity.util.JdbcTemplete;
import java.util.ArrayList;
import java.util.List;

public class ShoppingDAOImpl implements IShoppingDAO {
    @Override
    public void save(Shopping shopping) {
        String sql = "INSERT INTO shopping (name,count,price,totalPrice) VALUES (?,?,?,?)";
        JdbcTemplete.update(sql,shopping.getName(),shopping.getCount(),shopping.getPrice(),shopping.getTotalPrice());
    }

    @Override
    public void delete(String name) {
        String sql = "DELETE FROM shopping WHERE name = ?";
        JdbcTemplete.update(sql,name);
    }

    @Override
    public void update(Shopping shopping,String name) {
        String sql = "UPDATE shopping SET name = ?, count = ?, price = ?,totalPrice = ? WHERE name = ?";
        JdbcTemplete.update(sql,shopping.getName(),shopping.getCount(),shopping.getPrice(),shopping.getTotalPrice(),name);
    }

    @Override
    public Shopping getByName(String name) {
        String sql = "SELECT name,count,price,totalPrice FROM shopping WHERE name = ?";
        List<Shopping> shoppings = JdbcTemplete.query(sql, (rs) ->{
            List<Shopping> list = new ArrayList<>();
            while (rs.next()){
                Shopping shopping = new Shopping();
                list.add(shopping);
                shopping.setName(rs.getString("name"));
                shopping.setCount(rs.getInt("count"));
                shopping.setPrice(rs.getDouble("price"));
                shopping.setTotalPrice(rs.getDouble("totalPrice"));

            }
            return list;
        },name);
        return shoppings.size() == 1 ? shoppings.get(0) : null;
    }

    @Override
    public List<Shopping> listAll() {
        String sql = "SELECT name,count,price,totalPrice FROM shopping";
        List<Shopping> shoppings = JdbcTemplete.query(sql, (rs) ->{
            List<Shopping> list = new ArrayList<>();
            while (rs.next()){
                Shopping shopping = new Shopping();
                list.add(shopping);
                shopping.setName(rs.getString("name"));
                shopping.setCount(rs.getInt("count"));
                shopping.setPrice(rs.getDouble("price"));
                shopping.setTotalPrice(rs.getDouble("totalPrice"));
            }
            return list;
        });
        return shoppings;
    }

    @Override
    public void removeAll() {
        String sql = "DELETE FROM shopping";
        JdbcTemplete.update(sql);
    }
}
