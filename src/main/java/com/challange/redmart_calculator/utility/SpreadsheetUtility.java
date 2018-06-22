package com.challange.redmart_calculator.utility;

import com.challange.redmart_calculator.constant.SpreadsheetCalculatorConstants;

/**
 * Utility class for finding row number and column number using the reference string.
 * 
 * @author manjkuma
 *
 */
public class SpreadsheetUtility {

	/**
	 * @param rowStr reference value of the row
	 * @return the  row index vlaue.
	 */
	public static int getRowIndex(String rowStr) {
		int rowIndex = 0;
		for (int i = 0; i < rowStr.length(); i++) {
			rowIndex = rowIndex * SpreadsheetCalculatorConstants.MAX_ROWS + rowStr.charAt(i) - SpreadsheetCalculatorConstants.ASCII_OF_A + 1;
		}
		return (--rowIndex);
	}

	/**
	 * 
	 * @param rowStr holds the value of column in string format. 
	 * @return the column index in integer format.
	 */
	public static int getColIndex(String colStr) {
		return Integer.valueOf(colStr) - 1;
	}


}

