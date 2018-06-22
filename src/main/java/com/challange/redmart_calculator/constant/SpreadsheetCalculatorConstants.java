package com.challange.redmart_calculator.constant;

/**
 * This class holds constant for the SpreadSheet.
 * 
 * @author manjkuma
 *
 */
public final class SpreadsheetCalculatorConstants {
	
	/**
	 * status code in case of failure.
	 */
	public static final int FAILURE_STATUS = 1;
	
	/**
	 * status code in case of successful processing.
	 */
	public static final int SUCCESS_STATUS = 0;
	
	/**
	 * Used this if there is Circular dependency present i.e. if A is depend on B
	 * and B on A.
	 */
	public static final String CYCLIC_DEPENDENCY_PRESENT = "Cyclic Dependency Present";
	
	/**
	 * Total number of rows allowed.
	 */
	public static final int MAX_ROWS = 26;
	
	/**
	 * constant of ASCII value of A.
	 */
	public static final int ASCII_OF_A = 65;

}

