package com._520.webhomework.commodity.test;

import com._520.webhomework.commodity.dao.impl.CommodityDAOImpl;
import com._520.webhomework.commodity.query.PageResult;
import org.junit.jupiter.api.Test;


public class PageTest {

    private CommodityDAOImpl commodityDAO = new CommodityDAOImpl();
    @Test
    void test() throws Exception {

        Integer courentPage = 2;            // 当前页
        Integer totalPage = 5;              // 每页多少条数据
        System.out.println("当前页：" + courentPage + " 每页" + totalPage + "条数据");
        PageResult query = commodityDAO.query(courentPage, totalPage);
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
