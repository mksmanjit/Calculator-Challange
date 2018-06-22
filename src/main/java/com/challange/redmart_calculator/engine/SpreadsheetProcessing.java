package com.challange.redmart_calculator.engine;

/**
 * It is doing below steps for Spreadsheet processing.
 * 1) Reads data
 * 2) Evaluate the data
 * 3) Print the data
 * 
 * 
 * @author manjkuma
 *
 */
public interface SpreadsheetProcessing {
	
	/**
	 * Any body can call this method for processing the spreadsheet.It takes below steps.
     * 1) Reads data
     * 2) Evaluate the data
     * 3) Print the data
	 * 
	 * @return 0 if everthing processed successfully else 1.
	 */
	public int processSpreadsheet();

}
