package com.coding.sales;

import com.coding.sales.entity.Member;
import com.coding.sales.entity.Prod;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.OrderRepresentation;

import java.math.BigDecimal;
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
        //初始化数据
        UserUtil userDao = new UserUtil();

        //1.获取用户信息
        Member mem = userDao.getMember(command.getMemberId());
        if (mem == null) {
            throw new RuntimeException("用户不存在!");
        }
        //2.订单信息
        List<Prod> prods = OrderUtil.getProds(command);
        BigDecimal amount = OrderUtil.getAmount(prods, command.getDiscounts());
        for (Prod prod : prods) {

        }

        //3.积分
        userDao.updatePoint(amount, mem);

        //4.打印信息
        result = ProdUtil.getRepre(prods, mem, command, amount);

        return result;
    }


}
