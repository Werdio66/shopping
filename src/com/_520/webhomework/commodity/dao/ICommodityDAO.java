package com._520.webhomework.commodity.dao;

import com._520.webhomework.commodity.domain.Brand;
import com._520.webhomework.commodity.domain.Commodity;
import com._520.webhomework.commodity.query.CommodityQuery;
import com._520.webhomework.commodity.query.PageResult;

import java.util.List;

public interface ICommodityDAO {

    /**
     *  增加
     * @param commodity     商品
     */
    void save(Commodity commodity);

    /**
     *  删除商品
     * @param id        要删除商品的id
     */
    void delete(Long id);

    void update(Commodity commodity, Long id);

    Commodity get(Long id);

    Commodity getByName(String name);

    /**
     *  根据条件查询
     * @param commodityQuery    查询的条件
     * @return                  Commodity对象
     */
//    List<Commodity> query(CommodityQuery commodityQuery);

    Long getIdInBrand(String brandName);

    Integer getTotalCount();

    PageResult query(CommodityQuery commodityQuery);
}
