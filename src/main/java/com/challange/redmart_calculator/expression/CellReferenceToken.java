package com.challange.redmart_calculator.expression;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.challange.redmart_calculator.utility.SpreadsheetUtility;

/**
 * This class holds references of other cell on which it is depend.
 * 
 * @author manjkuma
 *
 */
public class CellReferenceToken extends Token {

	public static final String cellRefRegex = "([a-zA-Z]+)(\\d+)";
	
	/**
	 * Pattern object of regex.
	 */
	private static final Pattern cellRefPattern = Pattern.compile(cellRefRegex);

	/**
	 * holds the row number of the reference object.
	 */
	private int refRowIndex;
	/**
	 * holds the column number of reference object.
	 */
	private int refColIndex;

	/**
	 * constructor for setting the refRow and refCol.
	 * 
	 * @param str reference string
	 */
	public CellReferenceToken(String str) {
		setToken(str);
		Matcher matcher = cellRefPattern.matcher(str);

		if (matcher.matches()) {
			refRowIndex = SpreadsheetUtility.getRowIndex(matcher.group(1));
			refColIndex = SpreadsheetUtility.getColIndex(matcher.group(2));
		} else {
			throw new RuntimeException("Error: Unable to parse reference: " + str);
		}
	}
	
	/**
	 * @return the refRow
	 */
	public int getRefRow() {
		return refRowIndex;
	}

	/**
	 * @return the refCol
	 */
	public int getRefCol() {
		return refColIndex;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CellReferenceToken other = (CellReferenceToken) o;

		if (refColIndex != other.refColIndex) return false;
		if (refRowIndex != other.refRowIndex) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (String.valueOf(refRowIndex) + String.valueOf(refColIndex)).hashCode();
	}
}

