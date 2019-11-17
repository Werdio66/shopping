package com._520.webhomework.commodity.dao.impl;

import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.dao.RowMapper;
import com._520.webhomework.commodity.domain.Brand;
import com._520.webhomework.commodity.domain.Commodity;
import com._520.webhomework.commodity.query.CommodityQuery;
import com._520.webhomework.commodity.query.PageResult;
import com._520.webhomework.commodity.util.JdbcTemplete;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommodityDAOImpl implements ICommodityDAO {

    public Long getIdInBrand(String brandName){
        String sql = "SELECT id FROM brand WHERE name = ?";
        return JdbcTemplete.query(sql, (rs) -> {
            Long brandId = 0L;
            while (rs.next()) {
                brandId = rs.getLong("id");
            }
            return brandId;
        }, brandName);
    }
    public void save(Commodity commodity) {
        String sql = "INSERT INTO commodity (name,price,brand_id) VALUES (?,?,?)";
        JdbcTemplete.update(sql,commodity.getName(),commodity.getPrice(),getIdInBrand(commodity.getBrandName()));
    }

    public void delete(Long id) {
        String sql = "DELETE FROM commodity WHERE id = ?";
        JdbcTemplete.update(sql,id);
    }

    public void update(Commodity commodity, Long id) {
        String sql = "UPDATE commodity SET name = ?, price = ?, brand_id = ? WHERE id = ?";
        JdbcTemplete.update(sql,commodity.getName(),commodity.getPrice(),getIdInBrand(commodity.getBrandName()),id);
    }

    public Commodity get(Long id) {
        String sql = "SELECT commodity.id,commodity.name,price,brand.name brandName" +
                " FROM commodity JOIN brand " +
                "ON brand.id = commodity.brand_id " +
                "WHERE commodity.id = ?";
        List<Commodity> commoditys = JdbcTemplete.query(sql, new CommodityRowMapper(), id);
        return commoditys.size() == 1 ? commoditys.get(0) : null;
    }

    public Commodity getByName(String name) {
        String sql = "SELECT commodity.id,commodity.name,price,brand.name brandName" +
                " FROM commodity JOIN brand " +
                "ON brand.id = commodity.brand_id " +
                "WHERE commodity.name = ?";
        System.out.println(sql);
        List<Commodity> commoditys = JdbcTemplete.query(sql, new CommodityRowMapper(), name);
        return commoditys.size() == 1 ? commoditys.get(0) : null;
    }

    @Override
    public List<Commodity> query(CommodityQuery commodityQuery) {

        String newSql = commodityQuery.getSql();
        List<Object> parameters = commodityQuery.getParamers();
        String sql = "SELECT commodity.id,commodity.name,price,brand.name brandName" +
                " FROM commodity JOIN brand" +
                " ON brand.id = commodity.brand_id" + newSql;
        System.out.println(sql);
        System.out.println(parameters);
        return JdbcTemplete.query(sql,new CommodityRowMapper(), parameters.toArray());
    }


    public Integer getTotalCount(){
        // 查询结果总数
        String countSql = "SELECT COUNT(id) FROM commodity";
        Integer totalCount = JdbcTemplete.query(countSql,(rs) -> {
            Long count = 0L;
            if (rs.next()){
                count = rs.getLong("COUNT(id)");
            }
            return count;
        }).intValue();

        return totalCount;
    }

    @Override
    public PageResult query(Integer curentPage, Integer pageSize) {
        String countSql = "SELECT COUNT(id) FROM commodity";
        // 查询结果总数
        Integer totalCount = JdbcTemplete.query(countSql,(rs) -> {
            if (rs.next()){
                return rs.getLong("COUNT(id)");
            }
            return 0;
        }).intValue();

        // 没有数据
        if (totalCount == 0){
            return new PageResult(1,pageSize, Collections.EMPTY_LIST,totalCount);
        }
        // 查询结果集
        String sql = "SELECT * FROM commodity LIMIT ?,?";
        List<Object> query = JdbcTemplete.query(sql, (rs) -> {
            List<Object> list = new ArrayList<>();
            while (rs.next()) {
                Commodity commodity = new Commodity();
                list.add(commodity);
                commodity.setId(rs.getLong("id"));
                commodity.setName(rs.getString("name"));
                commodity.setPrice(rs.getDouble("price"));
//                commodity.setBrandName(rs.getString("brandName"));
            }
            return list;
        }, curentPage, pageSize);
        return new PageResult(curentPage,pageSize,query,totalCount);
    }


    private class CommodityRowMapper implements RowMapper<List<Commodity>>{

        @Override
        public List<Commodity> handle(ResultSet rs) throws Exception {
            List<Commodity> list = new ArrayList<>();
            while (rs.next()){
                Commodity commodity = new Commodity();
                list.add(commodity);
                commodity.setId(rs.getLong("id"));
                commodity.setName(rs.getString("name"));
                commodity.setPrice(rs.getDouble("price"));
                commodity.setBrandName(rs.getString("brandName"));
            }
            return list;
        };
    }
}
