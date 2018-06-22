package com.challange.redmart_calculator.engine;

import com.challange.redmart_calculator.model.CellDetail;

/**
 * This is used to process cell and update CellDetail with evaluated data. 
 * 
 * @author manjkuma
 *
 */
public interface CellProcessing {
	
	/**
	 * This method is evaluating each cell and update CellDetail with evaluated data.
	 * @param cellDetail holds object for the single Cell in a work sheet.
	 * @return return the evaluated value as double.
	 */
	public double evaluate(CellDetail cellDetail);

}
