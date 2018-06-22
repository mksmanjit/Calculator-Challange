package com.challange.redmart_calculator.writer.impl;

import com.challange.redmart_calculator.model.WorkSheet;
import com.challange.redmart_calculator.writer.OutputWriter;

/**
 * This class print the output on the console.
 * 
 * @author manjkuma
 *
 */
public class ConsoleWriter implements OutputWriter {

	@Override
	public void write(WorkSheet workBook) {
		System.out.print(workBook.toString());
	}

}