package com.convert.towords.vo;
public enum BritishNumericGroup {
	MILLION(" million ",0,3), THOUSAND(" thousand ",3,6), HUNDRED("",6,9);
	private int startIndex, endIndex;
	private String name;
	
	BritishNumericGroup(String name, int startIndex, int endIndex){
		this.name = name;
		this.startIndex=startIndex;
		this.endIndex=endIndex;
	}
	public int getStartIndex() {
		return startIndex;
	}
	public int getEndIndex() { 
		return endIndex; 
	}
	public String getName() {
		return name;
	}
}