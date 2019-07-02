package com.coding.sales.entity;

import java.math.BigDecimal;

/**
 * @Author: zhangyu
 * @Description: 常量类
 * @Date: 2019/7/2 16:53
 * @Modify:
 */
public class Constant {

    // 打折
    public static String DISCOUNT_TYPE_0 = "0";
    // 满减
    public static String DISCOUNT_TYPE_1 = "1";
    // 同时可用
    public static String DISCOUNT_TYPE_2 = "2";
    public static String DISCOUNT_95 = "95折券";
    public static String DISCOUNT_9 = "9折券";
    public static String DISCOUNT_3000 = "每满3000元减350";
    public static String DISCOUNT_2000 = "每满2000元减30";
    public static String DISCOUNT_1000 = "每满1000元减10";
    public static String DISCOUNT_THREE_HALF = "第3件半价";
    public static String DISCOUNT_THREE = "满3送1";
    public static String MEMBER_LEVEL_0 = "普卡";
    public static String MEMBER_LEVEL_1 = "金卡";
    public static String MEMBER_LEVEL_2 = "白金卡";
    public static String MEMBER_LEVEL_3 = "钻石卡";
    public static BigDecimal discountAmount = new BigDecimal(0.00);
    public static BigDecimal discountCardsAmount = new BigDecimal(0.00);
    public static BigDecimal oneThound = new BigDecimal(1000.00);
    public static BigDecimal twoThound = new BigDecimal(2000.00);
    public static BigDecimal threeThound = new BigDecimal(3000.00);
    public static BigDecimal ten = new BigDecimal(10.00);
    public static BigDecimal thirty = new BigDecimal(30.00);
    public static BigDecimal thirtyFive = new BigDecimal(35.00);
    public static BigDecimal three = new BigDecimal(3.00);
    public static BigDecimal four = new BigDecimal(4.00);
}
