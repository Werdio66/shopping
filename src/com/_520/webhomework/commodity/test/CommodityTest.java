package com._520.webhomework.commodity.test;

import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.domain.Commodity;
import com._520.webhomework.commodity.query.CommodityQuery;
import org.junit.jupiter.api.Test;
import java.util.List;

public class CommodityTest {
    private CommodityDAOImpl commodityDAO = new CommodityDAOImpl();
    private Commodity commodity = null;

    @Test
    void save(){
        commodity = new Commodity();
        commodity.setName("java从入门到放弃");
        commodity.setPrice(79D);
        commodity.setBrandName("书");
        commodityDAO.save(commodity);
    }

    @Test
    void delete(){

        commodityDAO.delete(14L);
    }

    @Test
    void update(){
        commodity = new Commodity();
        commodity.setName("java编程思想");
        commodity.setPrice(99D);
        commodity.setBrandName("书");
        commodityDAO.update(commodity,12L);
    }
    @Test
    void listAll() throws Exception {
//        List<Commodity> list = commodityDAO.listAll();
//        System.out.println(list);
    }

    @Test
    void get(){
        Commodity commodity = commodityDAO.get(3L);
        System.out.println(commodity);
    }

    @Test
    void getByName(){
        Commodity commodity = commodityDAO.getByName("手环");
        System.out.println(commodity);
    }

    @Test
    void query(){
//        CommodityQuery commodityQuery = new CommodityQuery();
//        commodityQuery.setName("手");
//        commodityQuery.setMinPrice(100.0);
//        commodityQuery.setMaxPrice(200.0);
//        List<Commodity> list = commodityDAO.query(commodityQuery);
//
//        list.forEach(System.out::println);
    }


    @Test
    void getIdInBrand(){
        System.out.println(commodityDAO.getIdInBrand("其他"));
    }
}
