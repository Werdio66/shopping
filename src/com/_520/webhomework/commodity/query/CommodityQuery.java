package com._520.webhomework.commodity.query;

import org.junit.platform.commons.util.StringUtils;
import java.util.ArrayList;
import java.util.List;

// 查询条件
public class CommodityQuery {

    private String name;
    private Double minPrice;
    private Double maxPrice;
    private String brand;
    private List<Object> list = new ArrayList<>();


    public List<Object> getParameters() {
        return list;
    }

    public String getSql() {
        StringBuilder sql = new StringBuilder("");
        if (StringUtils.isNotBlank(name)){
            sql.append(" AND name LIKE ?");
            list.add("%" + name + "%");
        }

        if (minPrice != null){
            sql.append(" AND price > ?");
            list.add(minPrice);
        }

        if (maxPrice != null){
            sql.append(" AND price < ?");
            list.add(maxPrice);
        }

        return sql.toString().replaceFirst("AND","WHERE");
    }





    @Override
    public String toString() {
        return "CommodityQuery{" +
                "name='" + name + '\'' +
                ", minPrice=" + minPrice +
                ", maxPrice=" + maxPrice +
                ", brand='" + brand + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
