package com.coding.sales;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.coding.sales.entity.Member;
import com.coding.sales.entity.MemberLevel;
/**
 * @author zhaozhuangzhuang
 * @date 20190702
 * **/

public class UserUtil {
	public static Map userMap = new HashMap<String,Member>();
	/**
	 * @param id
	 * @return Member
	 *   根据用户id获取用户信息
	 * **/
	public Member getMember(String id){
		if(userMap.get(id)!=null){
			return (Member) userMap.get(id);
		}else{
			return null;
		}
	}

	/**
	 * @param amount
	 * @param Member
	 *   修改积分，并更新客户等级
	 * **/
	public void updatePoint(BigDecimal amount,Member mem){
		if(amount.compareTo(new BigDecimal("1"))>0){
			BigDecimal addpoint =new BigDecimal("0");
			switch(mem.getMemberLevel()){
				case 普卡:
					addpoint=addpoint.add(amount);
					break;
				case 金卡:
					addpoint=addpoint.add(amount.multiply(new BigDecimal("1.5")));
					break;
				case 白金卡:
					addpoint=addpoint.add(amount.multiply(new BigDecimal("1.8")));
					break;
				case 钻石卡:
					addpoint=addpoint.add(amount.multiply(new BigDecimal("2")));
					break;
			}
			BigDecimal point = mem.getMemberPoints();
			mem.setAddPoint(addpoint);
			point=point.add(addpoint);
			mem.setMemberPoints(point);
			this.updateMemberLevel(mem);
		}else{
			mem.setAddPoint(new BigDecimal(0));
			mem.setOldLevel(mem.getMemberLevel());
		}
	}

	/**
	 * @param amount
	 * 更新余额
	 * **/
	public void updateMemberAmount(BigDecimal amt,Member mem){
		BigDecimal amount = mem.getAmount();
		if(amount.compareTo(amt)<0){
			throw new RuntimeException("余额不足！");
		}else{
			mem.setAmount(amount.subtract(amt));
		}
	}


	/**
	 * @param mem
	 * 更新用户等级
	 * **/
	public void updateMemberLevel(Member mem){
		BigDecimal point=mem.getMemberPoints();
		mem.setOldLevel(mem.getMemberLevel());
		if(point.compareTo(new BigDecimal("100000"))>=0){
			mem.setMemberLevel(MemberLevel.钻石卡);
		}else if(point.compareTo(new BigDecimal("50000"))>0){
			mem.setMemberLevel(MemberLevel.白金卡);
		}else if(point.compareTo(new BigDecimal("10000"))>0){
			mem.setMemberLevel(MemberLevel.金卡);
		}else{
			mem.setMemberLevel(MemberLevel.普卡);
		}
	}
    static{
        Member m=new Member();
        m.setMemberName("马丁");
        m.setMemberId("6236609999");
        m.setMemberLevel(MemberLevel.普卡);
        m.setAmount(new BigDecimal("999999999999"));
        m.setMemberPoints(new BigDecimal("9860"));
        m.setOldLevel(MemberLevel.普卡);
        userMap.put("6236609999", m);

        Member m1=new Member();
        m1.setMemberName("王立");
        m1.setMemberId("6630009999");
        m1.setMemberLevel(MemberLevel.金卡);
        m1.setOldLevel(MemberLevel.金卡);
        m1.setAmount(new BigDecimal("999999999999"));
        m1.setMemberPoints(new BigDecimal("48860"));
        userMap.put("6630009999", m1);

        Member m2=new Member();
        m2.setMemberName("李想");
        m2.setMemberId("8230009999");
        m2.setMemberLevel(MemberLevel.白金卡);
        m2.setOldLevel(MemberLevel.白金卡);
        m2.setAmount(new BigDecimal("999999999999"));
        m2.setMemberPoints(new BigDecimal("98860"));
        userMap.put("8230009999", m2);

        Member m3=new Member();
        m3.setMemberName("张三");
        m3.setMemberId("9230009999");
        m3.setMemberLevel(MemberLevel.钻石卡);
        m3.setOldLevel(MemberLevel.钻石卡);
        m3.setAmount(new BigDecimal("999999999999"));
        m3.setMemberPoints(new BigDecimal("198860"));
        userMap.put("9230009999", m3);
    }
}
