package com.coding.sales;

import com.coding.sales.entity.Constant;
import com.coding.sales.entity.Prod;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhangyu
 * @Description:
 * @Date: 2019/7/2 19:09
 * @Modify:
 */
public class OrderUtil {

    public static List<Prod> getProds(OrderCommand command) {
        List<OrderItemCommand> orderItemCommands = command.getItems();
        List<Prod> prodsList = new ArrayList<Prod>();
        for (OrderItemCommand item : orderItemCommands) {
            Prod prod = ProdUtil.getProd(item.getProduct());
            if (prod == null) {
                throw new RuntimeException("序列号为" + item.getProduct() + "的商品不存在!");
            }
            prod.setNum(item.getAmount());
            prodsList.add(prod);

        }
        return prodsList;
    }


    public static BigDecimal getAmount(List<Prod> prods, List<String> dis) {
        BigDecimal discountAmount = new BigDecimal(0.00);
        BigDecimal discountCardsAmount = new BigDecimal(0.00);

        // 只计算满减
        if (dis == null || dis.isEmpty()) {
            return getManjian(prods);
        } else {
            discountAmount = getManjian(prods);
            discountCardsAmount = getDiscount(prods, dis);
            return discountAmount.compareTo(discountCardsAmount) < 0 ? discountAmount : discountCardsAmount;


        }


    }


    public static BigDecimal getManjian(List<Prod> prods) {
        BigDecimal discountAmount = new BigDecimal(0.00);
        for (Prod prod : prods) {
            BigDecimal prdDiscountAmount = new BigDecimal(0.00);
            BigDecimal prdAmount = prod.getPrdPrice().multiply(prod.getNum());
            BigDecimal amount1 = new BigDecimal(0.00);
            BigDecimal amount2 = new BigDecimal(0.00);
            BigDecimal prdAmount1 = new BigDecimal(0.00);
            BigDecimal prdAmount2 = new BigDecimal(0.00);
            if (Constant.DISCOUNT_TYPE_0.equals(prod.getDiscountType()) || Constant.DISCOUNT_TYPE_2.equals(prod.getDiscountType())) {

                String[] discounts = prod.getDiscounts();
                if (discounts != null && discounts.length != 0) {
                    for (String discount : discounts) {

                        if (Constant.DISCOUNT_THREE.equals(discount) && prod.getNum().compareTo(Constant.four) >= 0) {
                            prdAmount1 = prdAmount.subtract(prod.getPrdPrice());
                            amount1 = prod.getPrdPrice();
                            break;
                        }
                        if (Constant.DISCOUNT_THREE_HALF.equals(discount) && prod.getNum().compareTo(Constant.three) >= 0) {
                            prdAmount1 = prdAmount.subtract(prod.getPrdPrice().divide(new BigDecimal(2)));
                            amount1 = prod.getPrdPrice().divide(new BigDecimal(2));
                        }


                    }
                    // er
                    BigDecimal a1 = new BigDecimal(0.00);
                    for (String discount : discounts) {
                        BigDecimal a2 = new BigDecimal(0.00);
                        if (Constant.DISCOUNT_3000.equals(discount) && prdAmount.compareTo(Constant.threeThound) >= 0) {
                            prdAmount2 = prdAmount.subtract(Constant.thirtyFive);
                            a2 = Constant.thirtyFive;


                        }
                        if (Constant.DISCOUNT_2000.equals(discount) && prdAmount.compareTo(Constant.twoThound) >= 0) {
                            prdAmount2 = prdAmount.subtract(Constant.thirty);
                            amount2 = Constant.thirty;

                        }
                        if (Constant.DISCOUNT_1000.equals(discount) && prdAmount.compareTo(Constant.oneThound) >= 0) {
                            prdAmount2 = prdAmount.subtract(Constant.ten);
                            amount2 = Constant.ten;

                        }
                        if (a2.compareTo(a1) >= 0) {
                            BigDecimal tmp = new BigDecimal(0.00);
                            a1 = tmp;
                            a1 = a2;
                            a2 = tmp;
                            prod.setUsedType(discount);

                        }

                    }
                    amount2 = a1;


                    if (amount1.compareTo(amount2) >= 0) {
                        prod.setDiscountAmount(amount1);
                        prdAmount = prdAmount1;
                    } else {
                        prod.setDiscountAmount(prdAmount.subtract(amount2));
                        prdAmount = prdAmount2;
                    }
                }


            }

            discountAmount = discountAmount.add(prdAmount);
        }
        return discountAmount;

    }


    public static BigDecimal getDiscount(List<Prod> prods, List<String> dis) {
        BigDecimal discountCardsAmount = new BigDecimal(0.00);
        for (Prod prod : prods) {
            BigDecimal prdAmount = prod.getPrdPrice().multiply(prod.getNum());
            BigDecimal amount = new BigDecimal(0.00);
            String[] discounts = prod.getDiscounts();
            if (discounts != null && discounts.length != 0) {
                for (String discount : discounts) {
                    for (String card : dis) {
                        if (card.equals(discount)) {
                            prod.setDiscountAmount(prdAmount.multiply(new BigDecimal(0.1)));
                            discountCardsAmount = discountCardsAmount.add(prdAmount.multiply(new BigDecimal(0.9)));
                            if (card.equals(Constant.DISCOUNT_9)) {
                                break;
                            }


                        }
                        if (card.equals(discount)) {
                            prod.setDiscountAmount(prdAmount.multiply(new BigDecimal(0.05)));
                            discountCardsAmount = discountCardsAmount.add(prdAmount.multiply(new BigDecimal(0.95)));


                        }
                    }
                }
            }

        }

        return discountCardsAmount;
    }
}
