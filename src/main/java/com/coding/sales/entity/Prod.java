package com.coding.sales.entity;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * @Author: zhangyu
 * @Description:
 * @Date: 2019/7/2 16:07
 * @Modify:
 */
public class Prod {

    private String prdId;
    private String prdName;
    private String prdUnit;
    private BigDecimal prdPrice;
    private String[] discounts;
    private String discountType;
    private BigDecimal num;


    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getPrdName() {
        return prdName;
    }

    public void setPrdName(String prdName) {
        this.prdName = prdName;
    }

    public String getPrdUnit() {
        return prdUnit;
    }

    public void setPrdUnit(String prdUnit) {
        this.prdUnit = prdUnit;
    }

    public BigDecimal getPrdPrice() {
        return prdPrice;
    }

    public void setPrdPrice(BigDecimal prdPrice) {
        this.prdPrice = prdPrice;
    }

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String[] getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String[] discounts) {
        this.discounts = discounts;
    }

    public BigDecimal getNum() {
        return num;
    }

    public void setNum(BigDecimal num) {
        this.num = num;
    }
}
