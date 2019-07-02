package com.coding.sales;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.coding.sales.entity.Prod;

public class ProdUtil {
	public static Map prodMap = new HashMap<String,Prod>();
	
	public static Prod getProd(String prodId){
		if(prodMap.get(prodId)!=null){
			return (Prod) prodMap.get(prodId);
		}else{
			return null;
		}
	}
	
	static{
		Prod p = new Prod();
		p.setPrdName("世园会五十国钱币册");
		p.setPrdId("001001");
		p.setPrdPrice(new BigDecimal("998"));
		p.setPrdUnit("册");
		prodMap.put("001001", p);
		
		p.setPrdName("2019北京世园会纪念银章大全40g");
		p.setPrdId("001002");
		p.setPrdPrice(new BigDecimal("1380"));
		p.setPrdUnit("盒");
		p.setDiscountType("0");
		String[] discounts=new String[]{"9折券"}; 
		p.setDiscounts(discounts);
		prodMap.put("001002", p);
		
		p.setPrdName("招财进宝");
		p.setPrdId("003001");
		p.setPrdPrice(new BigDecimal("1580"));
		p.setPrdUnit("条");
		p.setDiscountType("0");
		discounts=new String[]{"95折券"}; 
		p.setDiscounts(discounts);
		prodMap.put("003001", p);
		
		p.setPrdName("水晶之恋");
		p.setPrdId("003002");
		p.setPrdPrice(new BigDecimal("980"));
		p.setPrdUnit("条");
		p.setDiscountType("1");
		discounts=new String[]{"第3件半价","满3送1"}; 
		p.setDiscounts(discounts);
		prodMap.put("003002", p);
		
		p.setPrdName("中国经典钱币套装");
		p.setPrdId("002002");
		p.setPrdPrice(new BigDecimal("998"));
		p.setPrdUnit("套");
		p.setDiscountType("1");
		discounts=new String[]{"每满2000减30","每满1000减10"}; 
		p.setDiscounts(discounts);
		prodMap.put("002002", p);
		
		p.setPrdName("守扩之羽比翼双飞4.8g");
		p.setPrdId("002001");
		p.setPrdPrice(new BigDecimal("1080"));
		p.setPrdUnit("条");
		p.setDiscountType("2");
		discounts=new String[]{"第3件半价","满3送1","95折券"}; 
		p.setDiscounts(discounts);
		prodMap.put("002001", p);
		
		p.setPrdName("中国银象棋12g");
		p.setPrdId("002003");
		p.setPrdPrice(new BigDecimal("698"));
		p.setPrdUnit("套");
		p.setDiscountType("2");
		discounts=new String[]{"每满3000元减350","每满2000减30","每满1000减10","9折券"}; 
		p.setDiscounts(discounts);
		prodMap.put("002003", p);
		
	}
	
}
