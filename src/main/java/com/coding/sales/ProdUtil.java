package com.coding.sales;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.coding.sales.entity.Member;
import com.coding.sales.entity.Prod;
import com.coding.sales.input.OrderCommand;
import com.coding.sales.output.DiscountItemRepresentation;
import com.coding.sales.output.OrderItemRepresentation;
import com.coding.sales.output.OrderRepresentation;
import com.coding.sales.output.PaymentRepresentation;

public class ProdUtil {
	public static Map prodMap = new HashMap<String,Prod>();
	
	public static Prod getProd(String prodId){
		if(prodMap.get(prodId)!=null){
			return (Prod) prodMap.get(prodId);
		}else{
			return null;
		}
	}

	public static OrderRepresentation getRepre(List<Prod> prods, Member mem, OrderCommand command,BigDecimal amount){
       BigDecimal a = new BigDecimal(0.00);
        List<OrderItemRepresentation> items = new ArrayList<OrderItemRepresentation>();
        List<DiscountItemRepresentation> discounts = new ArrayList<DiscountItemRepresentation>();
        List<PaymentRepresentation> payments = new ArrayList<PaymentRepresentation>();
        for (Prod prod:prods){
            OrderItemRepresentation o = new OrderItemRepresentation(prod.getPrdId(), prod.getPrdName(), prod.getPrdPrice(), prod.getNum(), prod.getNum().multiply(prod.getPrdPrice()));
            items.add(o);
            DiscountItemRepresentation d = new DiscountItemRepresentation(prod.getPrdId(), prod.getPrdName(), prod.getDiscountAmount());
            discounts.add(d);
            a= a.add(prod.getDiscountAmount());
       }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date =  sdf.parse(command.getCreateTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        OrderRepresentation result =new OrderRepresentation(command.getOrderId(), date, mem.getMemberId(), mem.getMemberName(), mem.getOldLevel().toString(), mem.getMemberLevel().toString(),
                mem.getAddPoint().intValue(), mem.getMemberPoints().intValue(), items, a.add(amount), discounts,
                a, amount, payments, command.getDiscounts());
	    return result;
    }

    static{
        Prod p = new Prod();
        p.setPrdName("世园会五十国钱币册");
        p.setPrdId("001001");
        p.setPrdPrice(new BigDecimal("998"));
        p.setPrdUnit("册");
        prodMap.put("001001", p);

        Prod p1 = new Prod();
        p1.setPrdName("2019北京世园会纪念银章大全40g");
        p1.setPrdId("001002");
        p1.setPrdPrice(new BigDecimal("1380"));
        p1.setPrdUnit("盒");
        p1.setDiscountType("0");
        String[] discounts=new String[]{"9折券"};
        p1.setDiscounts(discounts);
        prodMap.put("001002", p1);

        Prod p2 = new Prod();
        p2.setPrdName("招财进宝");
        p2.setPrdId("003001");
        p2.setPrdPrice(new BigDecimal("1580"));
        p2.setPrdUnit("条");
        p2.setDiscountType("0");
        discounts=new String[]{"95折券"};
        p2.setDiscounts(discounts);
        prodMap.put("003001", p2);

        Prod p3 = new Prod();
        p3.setPrdName("水晶之恋");
        p3.setPrdId("003002");
        p3.setPrdPrice(new BigDecimal("980"));
        p3.setPrdUnit("条");
        p3.setDiscountType("1");
        discounts=new String[]{"第3件半价","满3送1"};
        p3.setDiscounts(discounts);
        prodMap.put("003002", p3);

        Prod p4 = new Prod();
        p4.setPrdName("中国经典钱币套装");
        p4.setPrdId("002002");
        p4.setPrdPrice(new BigDecimal("998"));
        p4.setPrdUnit("套");
        p4.setDiscountType("1");
        discounts=new String[]{"每满2000减30","每满1000减10"};
        p4.setDiscounts(discounts);
        prodMap.put("002002", p4);

        Prod p5 = new Prod();
        p5.setPrdName("守扩之羽比翼双飞4.8g");
        p5.setPrdId("002001");
        p5.setPrdPrice(new BigDecimal("1080"));
        p5.setPrdUnit("条");
        p5.setDiscountType("2");
        discounts=new String[]{"第3件半价","满3送1","95折券"};
        p5.setDiscounts(discounts);
        prodMap.put("002001", p5);

        Prod p6 = new Prod();
        p6.setPrdName("中国银象棋12g");
        p6.setPrdId("002003");
        p6.setPrdPrice(new BigDecimal("698"));
        p6.setPrdUnit("套");
        p6.setDiscountType("2");
        discounts=new String[]{"每满3000元减350","每满2000减30","每满1000减10","9折券"};
        p6.setDiscounts(discounts);
        prodMap.put("002003", p6);

    }
	
}
