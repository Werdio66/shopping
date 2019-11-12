package com._520.webhomework.commodity.domain;

import java.math.BigDecimal;

// 购物车
public class Shopping {
    private String name;
    private Integer count;
    private Double price;
    private Double totalPrice;

    @Override
    public String toString() {
        return "Shopping{" +
                "name='" + name + '\'' +
                ", count=" + count +
                ", price=" + price +
                '}';
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTotalPrice() {

         return this.getCount() * this.getPrice();
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
