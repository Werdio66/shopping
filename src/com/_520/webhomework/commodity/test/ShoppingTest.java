package com._520.webhomework.commodity.test;

import com._520.webhomework.commodity.dao.ICommodityDAO;
import com._520.webhomework.commodity.dao.IShoppingDAO;
import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.dao.impl.ShoppingDAOImpl;
import com._520.webhomework.commodity.domain.Shopping;
import org.junit.jupiter.api.Test;

public class ShoppingTest {
    private static IShoppingDAO shoppingDAO = new ShoppingDAOImpl();
    private static Shopping shopping;
    private static ICommodityDAO commodityDAO = new CommodityDAOImpl();

    @Test
    void save(){
        shopping = new Shopping();
        shopping.setName(commodityDAO.get(1L).getName());
        shopping.setCount(1);
        shopping.setPrice(commodityDAO.get(1L).getPrice());
        shoppingDAO.save(shopping);

        System.out.println(shoppingDAO.listAll());
    }

    @Test
    void delete(){
        shoppingDAO.delete("鼠标");
        System.out.println(shoppingDAO.listAll());

    }

    @Test
    void update(){
        shopping = new Shopping();
        shopping.setName(commodityDAO.get(2L).getName());
        shopping.setCount(1);
        shopping.setPrice(commodityDAO.get(2L).getPrice());
        shoppingDAO.update(shopping,"鼠标");
    }
    @Test
    void listAll() throws Exception {
        System.out.println(shoppingDAO.listAll());
    }

    @Test
    void get(){
        System.out.println(shoppingDAO.getByName("鼠标"));
        System.out.println(shoppingDAO.listAll());

    }
    @Test
    void removeAll(){
        shoppingDAO.removeAll();
        System.out.println(shoppingDAO.listAll());

    }
}
