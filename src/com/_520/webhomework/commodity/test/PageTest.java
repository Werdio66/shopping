package com._520.webhomework.commodity.test;

import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.query.CommodityQuery;
import com._520.webhomework.commodity.query.PageResult;
import org.junit.jupiter.api.Test;


public class PageTest {

    private CommodityDAOImpl commodityDAO = new CommodityDAOImpl();
    @Test
    void test() throws Exception {
//
        CommodityQuery commodityQuery = new CommodityQuery();
//        commodityQuery.setName("手");
//        commodityQuery.setMinPrice(100.0);
        commodityQuery.setMaxPrice(200.0);

        PageResult query = commodityDAO.query(commodityQuery);
        query.getListDates().forEach(System.out::println);
        System.out.println("起始页："+query.getBeginPage());
        System.out.println("---------------------------");
        System.out.println("上一页："+query.getPrevPage());
        System.out.println("当前页："+query.getCourentPage());
        System.out.println("下一页："+query.getNextPage());
        System.out.println("--------------------------");
        System.out.println("每页数据："+query.getPageSize());
        System.out.println("总页数："+query.getTotalPage());
        System.out.println("总条数："+query.getTotalCount());
    }
}
