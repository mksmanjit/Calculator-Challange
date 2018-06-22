package com.challange.redmart_calculator.main;

import com.challange.redmart_calculator.engine.impl.RPNCellProcessingImpl;
import com.challange.redmart_calculator.engine.impl.SpreadsheetProcessingImpl;
import com.challange.redmart_calculator.engine.impl.WorksheetProcessingImpl;
import com.challange.redmart_calculator.input.impl.ConsoleInputReader;
import com.challange.redmart_calculator.writer.impl.ConsoleWriter;

/**
 * This is the entry point of the Program.
 * 
 * @author manjkuma
 *
 */
public class Spreadsheet {

	/**
	 * Holds the object for the doing spreadsheet processing.
	 */
	private SpreadsheetProcessingImpl spreadsheetProcessing;

	/**
	 * Constructor used for dependency injection.
	 */
	public Spreadsheet() {
		this.spreadsheetProcessing = new SpreadsheetProcessingImpl(
				new WorksheetProcessingImpl(new RPNCellProcessingImpl()), new ConsoleInputReader(), new ConsoleWriter());
	}

	/**
	 * Start point of the program.
	 * 
	 * @param args holds command line argument
	 */
	public static void main(String[] args) {
		Spreadsheet spreadsheet = new Spreadsheet();
		spreadsheet.spreadsheetProcessing.processSpreadsheet();
	}

}
