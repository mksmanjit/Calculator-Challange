package com.challange.redmart_calculator.engine.impl;

import com.challange.redmart_calculator.constant.SpreadsheetCalculatorConstants;
import com.challange.redmart_calculator.engine.SpreadsheetProcessing;
import com.challange.redmart_calculator.engine.WorksheetProcessing;
import com.challange.redmart_calculator.exception.CyclicDependencyException;
import com.challange.redmart_calculator.input.InputReader;
import com.challange.redmart_calculator.model.WorkSheet;
import com.challange.redmart_calculator.writer.OutputWriter;

public class SpreadsheetProcessingImpl implements SpreadsheetProcessing {
	
	/**
	 * Object for doing work sheet processing.
	 */
	private WorksheetProcessing worksheetProcessing;
	/**
	 * Object for reading input.
	 */
	private InputReader inputReader;
	/**
	 * Object for printing output.
	 */
	private OutputWriter writer;
	
	/**
	 * Inject dependencies during object creation.
	 * 
	 * @param worksheetProcessing object for doing work sheet processing.
	 * @param inputReader object for reading input.
	 * @param writer object for printing output.
	 */
	public SpreadsheetProcessingImpl(WorksheetProcessing worksheetProcessing, InputReader inputReader, OutputWriter writer) {
		this.worksheetProcessing = worksheetProcessing;
		this.inputReader = inputReader;
		this.writer = writer;
	}

	@Override
	public int processSpreadsheet() {
		WorkSheet workBook = inputReader.readInput();

		try {
			worksheetProcessing.processWorksheet(workBook);
		} catch (RuntimeException | CyclicDependencyException re) {
			re.printStackTrace();
		}

		writer.write(workBook);
		if (workBook.isCyclicDependent()) {
			return SpreadsheetCalculatorConstants.FAILURE_STATUS;
		}
		return SpreadsheetCalculatorConstants.SUCCESS_STATUS;
	}
	
}

