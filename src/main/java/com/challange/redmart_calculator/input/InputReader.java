package com.challange.redmart_calculator.input;

import com.challange.redmart_calculator.model.WorkSheet;

/**
 * This class is used to read input and return the populated WorkBook object.
 * @author manjkuma
 *
 */
public interface InputReader {
	
	/**
	 * @return WorkBook object which get created by populating with the input value.
	 */
	public WorkSheet readInput();

}
