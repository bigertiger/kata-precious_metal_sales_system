package com.coding.sales.entity;


public class DisCount {
	private String disCountName;
	private String disCountType;
	private DisCountDetail[]  disCountValue;
	
	
	public String getDisCountName() {
		return disCountName;
	}
	public void setDisCountName(String disCountName) {
		this.disCountName = disCountName;
	}
	public String getDisCountType() {
		return disCountType;
	}
	public void setDisCountType(String disCountType) {
		this.disCountType = disCountType;
	}
	public DisCountDetail[] getDisCountValue() {
		return disCountValue;
	}
	public void setDisCountValue(DisCountDetail[] disCountValue) {
		this.disCountValue = disCountValue;
	}
	
	
}
