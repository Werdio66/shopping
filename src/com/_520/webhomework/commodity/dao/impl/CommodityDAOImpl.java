package com._520.webhomework.commodity.dao.impl;

import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.domain.Commodity;
import com._520.webhomework.commodity.query.CommodityQuery;
import com._520.webhomework.commodity.util.JdbcTemplete;
import java.util.ArrayList;
import java.util.List;

public class CommodityDAOImpl implements ICommodityDAO {

    public void save(Commodity commodity) {
        String sql = "INSERT INTO commodity (name,price) VALUES (?,?)";
        JdbcTemplete.update(sql,commodity.getName(),commodity.getPrice());
    }

    public void delete(Long id) {
        String sql = "DELETE FROM commodity WHERE id = ?";
        JdbcTemplete.update(sql,id);
    }

    public void update(Commodity commodity, Long id) {
        String sql = "UPDATE commodity SET name = ?, price = ? WHERE id = ?";
        JdbcTemplete.update(sql,commodity.getName(),commodity.getPrice(),id);
    }

    public Commodity get(Long id) {
        String sql = "SELECT id,name,price FROM commodity WHERE id = ?";
        List<Commodity> commoditys = JdbcTemplete.query(sql, (rs) -> {
                List<Commodity> list = new ArrayList<>();
                while (rs.next()){
                    Commodity commodity = new Commodity();
                    list.add(commodity);
                    commodity.setId(rs.getLong("id"));
                    commodity.setName(rs.getString("name"));
                    commodity.setPrice(rs.getDouble("price"));
                }
                return list;
        }, id);
        return commoditys.size() == 1 ? commoditys.get(0) : null;
    }

    public Commodity getByName(String name) {
        String sql = "SELECT id,name,price FROM commodity WHERE name = ?";
        List<Commodity> commoditys = JdbcTemplete.query(sql, (rs) -> {
            List<Commodity> list = new ArrayList<>();
            while (rs.next()){
                Commodity commodity = new Commodity();
                list.add(commodity);
                commodity.setId(rs.getLong("id"));
                commodity.setName(rs.getString("name"));
                commodity.setPrice(rs.getDouble("price"));
            }
            return list;
        }, name);
        return commoditys.size() == 1 ? commoditys.get(0) : null;
    }

    @Override
    public List<Commodity> query(CommodityQuery commodityQuery) {

        String newSql = commodityQuery.getSql();
        List<Object> parameters = commodityQuery.getParameters();
        String sql = "SELECT id,name,price FROM commodity" + newSql;
        System.out.println(sql);
        System.out.println(parameters);
        return JdbcTemplete.query(sql,(rs) ->{
            List<Commodity> list = new ArrayList<>();
            while (rs.next()){
                Commodity commodity = new Commodity();
                list.add(commodity);
                commodity.setId(rs.getLong("id"));
                commodity.setName(rs.getString("name"));
                commodity.setPrice(rs.getDouble("price"));
            }
            return list;
        }, parameters.toArray());
    }


    public List<Commodity> listAll() throws Exception {
        String sql = "SELECT id,name,price FROM commodity";
        return JdbcTemplete.query(sql,(rs) ->{
            List<Commodity> list = new ArrayList<>();
            while (rs.next()){
                Commodity commodity = new Commodity();
                list.add(commodity);
                commodity.setId(rs.getLong("id"));
                commodity.setName(rs.getString("name"));
                commodity.setPrice(rs.getDouble("price"));
            }
            return list;
        });
    }

}
