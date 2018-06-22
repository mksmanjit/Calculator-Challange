package com.challange.redmart_calculator.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.Test;

import com.challange.redmart_calculator.engine.WorksheetProcessing;
import com.challange.redmart_calculator.engine.impl.RPNCellProcessingImpl;
import com.challange.redmart_calculator.engine.impl.WorksheetProcessingImpl;
import com.challange.redmart_calculator.exception.CyclicDependencyException;
import com.challange.redmart_calculator.input.InputReader;
import com.challange.redmart_calculator.input.impl.ConsoleInputReader;
import com.challange.redmart_calculator.model.WorkSheet;

/**
 * Class just to bundle up testing against different files.
 */
public class SpreadsheetTest {

	@Test
	public void testCase1() {
		WorksheetProcessing worksheetProcessing = new WorksheetProcessingImpl(new RPNCellProcessingImpl());
		String input = "3 2" + System.lineSeparator() + "A2" + System.lineSeparator() + "4 5 *" + System.lineSeparator()
				+ "A1" + System.lineSeparator() + "A1 B2 / 2 +" + System.lineSeparator() + "3" + System.lineSeparator()
				+ "39 B1 B2 * /";
		String output = "3 2" + System.lineSeparator() + "20.00000" + System.lineSeparator() + "20.00000"
				+ System.lineSeparator() + "20.00000" + System.lineSeparator() + "8.66667" + System.lineSeparator()
				+ "3.00000" + System.lineSeparator() + "1.50000";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputReader reader = new ConsoleInputReader();
		WorkSheet workBook = reader.readInput();
		try {
			worksheetProcessing.processWorksheet(workBook);
		} catch (CyclicDependencyException e) {
		}
		assertEquals(output, workBook.toString());
	}
	
	@Test
	public void testCase2() {
		WorksheetProcessing worksheetProcessing = new WorksheetProcessingImpl(new RPNCellProcessingImpl());
		String input = "3 2" + System.lineSeparator() + "A2" + System.lineSeparator() + "4 5 * A1 /"
				+ System.lineSeparator() + "A1" + System.lineSeparator() + "A1 B2 / 2 +" + System.lineSeparator() + "3"
				+ System.lineSeparator() + "39 B1 B2 * /";
		String output = "3 2" + System.lineSeparator() + "Cyclic Dependency Present" + System.lineSeparator()
				+ "Cyclic Dependency Present" + System.lineSeparator() + "Cyclic Dependency Present" + System.lineSeparator()
				+ "Cyclic Dependency Present" + System.lineSeparator() + "3.00000" + System.lineSeparator()
				+ "Cyclic Dependency Present";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputReader reader = new ConsoleInputReader();
		WorkSheet workBook = reader.readInput();
		try {
			worksheetProcessing.processWorksheet(workBook);
		} catch (CyclicDependencyException e) {
		}
		assertEquals(output, workBook.toString());
	}
	
	@Test
	public void testCase3() {
		WorksheetProcessing worksheetProcessing = new WorksheetProcessingImpl(new RPNCellProcessingImpl());
		String input = "3 3" + System.lineSeparator() + "2 3 *" + System.lineSeparator() + "C3 A2 -"
				+ System.lineSeparator() + "C1 B1 -" + System.lineSeparator() + "A1 B2 +" + System.lineSeparator()
				+ "7 1 -" + System.lineSeparator() + "8 1 +" + System.lineSeparator() + "A1 B3 +"
				+ System.lineSeparator() + "4 5 * 2 /" + System.lineSeparator() + "6 2 -";
		String output = "3 3" + System.lineSeparator() + "6.00000" + System.lineSeparator()
				+ "Cyclic Dependency Present" + System.lineSeparator() + "3.00000" + System.lineSeparator()
				+ "12.00000" + System.lineSeparator() + "6.00000" + System.lineSeparator() + "9.00000"
				+ System.lineSeparator() + "15.00000" + System.lineSeparator() + "10.00000" + System.lineSeparator()
				+ "4.00000";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputReader reader = new ConsoleInputReader();
		WorkSheet workBook = reader.readInput();
		try {
			worksheetProcessing.processWorksheet(workBook);
		} catch (CyclicDependencyException e) {
		}
		assertEquals(output, workBook.toString());
	}
	
	@Test
	public void testCase4() {
		WorksheetProcessing worksheetProcessing = new WorksheetProcessingImpl(new RPNCellProcessingImpl());
		String input = "3 3" + System.lineSeparator() + "2 3 *" + System.lineSeparator() + "C3 C1 -"
				+ System.lineSeparator() + "C1 B1 -" + System.lineSeparator() + "A1 B2 +" + System.lineSeparator()
				+ "7 1 -" + System.lineSeparator() + "8 1 +" + System.lineSeparator() + "A1 B3 +"
				+ System.lineSeparator() + "4 5 * 2 /" + System.lineSeparator() + "6 2 -";
		String output = "3 3" + System.lineSeparator() + "6.00000" + System.lineSeparator() + "-11.00000" + System.lineSeparator() + "3.00000"
				+ System.lineSeparator() + "12.00000" + System.lineSeparator() + "6.00000" + System.lineSeparator()
				+ "9.00000" + System.lineSeparator() + "15.00000" + System.lineSeparator() + "10.00000"
				+ System.lineSeparator() + "4.00000";
		InputStream in = new ByteArrayInputStream(input.getBytes());
		System.setIn(in);
		InputReader reader = new ConsoleInputReader();
		WorkSheet workBook = reader.readInput();
		try {
			worksheetProcessing.processWorksheet(workBook);
		} catch (CyclicDependencyException e) {
		}
		assertEquals(output, workBook.toString());
	}
}


