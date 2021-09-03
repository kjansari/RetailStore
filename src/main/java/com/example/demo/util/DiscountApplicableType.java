package com.example.demo.util;

public enum DiscountApplicableType {
	NOT_APPLICABALE("N"),APPLICABALE("Y");
	private String typeCode;
	
	DiscountApplicableType(String typeCode) {
		this.typeCode=typeCode;
	}

	public String getTypeCode() {
		return typeCode;
	}

	
}
