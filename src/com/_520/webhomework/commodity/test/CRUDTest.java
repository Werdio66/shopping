package com._520.webhomework.commodity.test;

import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.domain.Commodity;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

public class CRUDTest {
    private CommodityDAOImpl commodityDAO = new CommodityDAOImpl();
    private Commodity commodity = null;

    @Test
    void save(){
        commodity = new Commodity();
        commodity.setName("手机");
        commodity.setPrice(66D);
        commodityDAO.save(commodity);
    }

    @Test
    void delete(){

        commodityDAO.delete(7L);
    }

    @Test
    void update(){
        commodity = new Commodity();
        commodity.setName("充电宝");
        commodity.setPrice(66D);
        commodityDAO.update(commodity,5L);
    }
    @Test
    void listAll() throws Exception {
        List<Commodity> list = commodityDAO.listAll();
        System.out.println(list);
    }

    @Test
    void get(){
        Commodity commodity = commodityDAO.get(2L);
        System.out.println(commodity);
    }
}
