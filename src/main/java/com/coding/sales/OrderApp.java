package com.coding.sales;

import com.coding.sales.entity.Constant;
import com.coding.sales.entity.Prod;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.input.OrderItemCommand;
import com.coding.sales.output.OrderRepresentation;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 销售系统的主入口
 * 用于打印销售凭证
 */
public class OrderApp {

    public static void main(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("参数不正确。参数1为销售订单的JSON文件名，参数2为待打印销售凭证的文本文件名.");
        }

        String jsonFileName = args[0];
        String txtFileName = args[1];

        String orderCommand = FileUtils.readFromFile(jsonFileName);
        OrderApp app = new OrderApp();
        String result = app.checkout(orderCommand);
        FileUtils.writeToFile(result, txtFileName);
    }

    public String checkout(String orderCommand) {
        OrderCommand command = OrderCommand.from(orderCommand);
        OrderRepresentation result = checkout(command);

        return result.toString();
    }

    OrderRepresentation checkout(OrderCommand command) {
        OrderRepresentation result = null;
        List<Prod> prods = getProds(command);


        /*1 解析商品数据
          2 根据不同的优惠 计算出应付金额

         {
          3 比较应付金额  选择优惠最大的  根据实付金额 累积积分(如果累积后客户可以升级 则升级)
          4 扣款
          5 组装打印单据类的数据 进行打印

         }
         */


        //TODO: 请完成需求指定的功能

        return result;
    }

    List<Prod> getProds(OrderCommand command) {
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


    BigDecimal getAmount(List<Prod> prods, List<String> dis) {
        BigDecimal discountAmount = new BigDecimal(0.00);
        BigDecimal discountCardsAmount = new BigDecimal(0.00);

        // 只计算满减
        if (dis == null || dis.isEmpty()) {
            return getManjian(prods);
        } else {
            discountAmount = getManjian(prods);


        }


        return null;
    }


    BigDecimal getManjian(List<Prod> prods) {
        BigDecimal discountAmount = new BigDecimal(0.00);
        for (Prod prod : prods) {
            BigDecimal prdAmount = prod.getPrdPrice().multiply(prod.getNum());
            if (Constant.DISCOUNT_TYPE_0.equals(prod.getDiscountType()) || Constant.DISCOUNT_TYPE_2.equals(prod.getDiscountType())) {

                String[] discounts = prod.getDiscounts();
                for (String discount : discounts) {
                    if (Constant.DISCOUNT_THREE.equals(discount) && prod.getNum().compareTo(Constant.four) >= 0) {
                        prdAmount = prdAmount.subtract(prod.getPrdPrice());
                    }
                    if (Constant.DISCOUNT_THREE_HALF.equals(discount) && prod.getNum().compareTo(Constant.three) >= 0) {
                        prdAmount = prdAmount.subtract(prod.getPrdPrice().divide(new BigDecimal(2)));
                    }
                    if (Constant.DISCOUNT_3000.equals(discount) && prdAmount.compareTo(Constant.threeThound) >= 0) {
                        prdAmount = prdAmount.subtract(Constant.thirtyFive);
                    }
                    if (Constant.DISCOUNT_2000.equals(discount) && prdAmount.compareTo(Constant.twoThound) >= 0) {
                        prdAmount = prdAmount.subtract(Constant.thirty);
                    }
                    if (Constant.DISCOUNT_1000.equals(discount) && prdAmount.compareTo(Constant.oneThound) >= 0) {
                        prdAmount = prdAmount.subtract(Constant.ten);
                    }
                }
            }

            discountAmount = discountAmount.add(prdAmount);
        }
        return discountAmount;

    }


}
