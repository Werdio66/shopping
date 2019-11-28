package com._520.webhomework.commodity.query;

import org.junit.platform.commons.util.StringUtils;

// 查询条件
public class CommodityQuery extends QueryObject {

    private String name;            // 按名称模糊查询
    private Double minPrice;        // 最低价
    private Double maxPrice;        // 最高价
    private String brandName;       // 类型
    private String keyword;         // 关键字查询



    // 自定义查询
    @Override
    protected void cunstomizedQuery() {

        if (StringUtils.isNotBlank(name)){
            super.addQuery("commodity.name LIKE ?","%" + name + "%");
        }

        if (minPrice != null){
            super.addQuery("price > ?",minPrice);
        }

        if (maxPrice != null){
            super.addQuery("price < ?",maxPrice);
        }

        if (StringUtils.isNotBlank(brandName)){
            super.addQuery("brand.name = ?",brandName);
        }

        if (StringUtils.isNotBlank(keyword)){
            super.addQuery("(brand.name LIKE ? or commodity.name LIKE ?)",
                    "%" + keyword + "%","%" + keyword + "%");
        }
    }




    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
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

}
