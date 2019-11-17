package com._520.webhomework.commodity.domain;

// 商品分类
public class Brand {
    private String brandName;
    private Long brandId;


    @Override
    public String toString() {
        return "Brand{" +
                "brandName='" + brandName + '\'' +
                ", brandId=" + brandId +
                '}';
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }
}
