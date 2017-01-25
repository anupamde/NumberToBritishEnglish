package com.convert.towords.vo;

public class NumberToProcess {
	private String numericGroupName;
	private int intToProcess;

	public NumberToProcess(String numericGroupName, int intToProcess) {
		super();
		this.numericGroupName = numericGroupName;
		this.intToProcess = intToProcess;
	}
	/**
	 * @return the numericGroup
	 */
	public String getNumericGroupName() {
		return numericGroupName;
	}
	/**
	 * @return the intToProcess
	 */
	public int getIntToProcess() {
		return intToProcess;
	}
}
