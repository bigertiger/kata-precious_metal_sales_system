package com.coding.sales.entity;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * @Author: zhangyu
 * @Description:
 * @Date: 2019/7/2 15:56
 * @Modify:
 */
public class Member {
    private String memberId;
    private String memberName;
    private BigDecimal amount;


    private String memberLevel;
    private BigDecimal memberPoints;

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getMemberLevel() {
        return memberLevel;
    }

    public void setMemberLevel(String memberLevel) {
        this.memberLevel = memberLevel;
    }

    public BigDecimal getMemberPoints() {
        return memberPoints;
    }

    public void setMemberPoints(BigDecimal memberPoints) {
        this.memberPoints = memberPoints;
    }


    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberName='" + memberName + '\'' +
                ", amount=" + amount +
                ", memberLevel='" + memberLevel + '\'' +
                ", memberPoints=" + memberPoints +
                '}';
    }

}
