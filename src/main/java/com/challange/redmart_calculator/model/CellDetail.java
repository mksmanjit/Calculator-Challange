package com.challange.redmart_calculator.model;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import com.challange.redmart_calculator.expression.Token;
import com.challange.redmart_calculator.expression.CellReferenceToken;
import com.challange.redmart_calculator.factory.TokenFactory;

/**
 * This class holds the Detail of a Cell.
 * 
 * @author manjkuma
 *
 */
public class CellDetail {
	/**
	 * Pattern of matching one or more spaces.
	 */
	private final Pattern splitRegex = Pattern.compile("\\s+");
	/**
	 * row index of the cell.
	 */
	private final int rowIndex;
	/**
	 * column index of the cell.
	 */
	private final int columnIndex;
	/**
	 * List of Reference Cell on which this cell is depend.
	 */
	private final List<CellReferenceToken> cellReferencesTokens;
	/**
	 * Holds the list of token value for this row.
	 */
	private final List<Token> cellTokens;
	/**
	 * Holds the full content of this cell.
	 */
	private final String contents;
	/**
	 * Holds the number of unresolved reference on which this cell is depend.
	 */
	private int unresolvedRefs;
	/**
	 * true if Cell is evaluated successfully else false.
	 */
	private boolean evaluated;
	/**
	 * Holds the evaluated value after applying operators.
	 */
	private double evaluatedValue;
	/**
	 * Holds the reference of work book under which this cell is present.
	 */
	private WorkSheet workBook;
	/**
	 * Constructor for setting CellDetail value.
	 * 
	 * @param rowIndex row index of the cell.
	 * @param colIndex column index of the cell.
	 * @param contents full content of this cell.
	 * @param workBook reference of work book under which this cell is present.
	 */
	public CellDetail(int rowIndex, int colIndex, String contents, WorkSheet workBook) {
		this.rowIndex = rowIndex;
		this.columnIndex = colIndex;
		this.contents = contents;
		this.unresolvedRefs = 0;
		this.cellReferencesTokens = new LinkedList<CellReferenceToken>();
		this.cellTokens = new LinkedList<Token>();
		this.workBook = workBook;
		this.parse();
	}
	/**
	 * This method parse the full content of this cell and divide this into token.
	 */
	private void parse() {
		String[] expressions = splitRegex.split(contents);
		TokenFactory expressionFactory = new TokenFactory();
		for (String expression : expressions) {
			Token token = expressionFactory.createTokenObject(expression);
			if (token instanceof  CellReferenceToken) {
				cellReferencesTokens.add(((CellReferenceToken) token));
				unresolvedRefs++;
			}
			cellTokens.add(token);
		}
	}
	
	/**
	 * @return the unresolvedRefs
	 */
	public int getUnresolvedRefs() {
		return unresolvedRefs;
	}

	/**
	 * @param unresolvedRefs the unresolvedRefs to set
	 */
	public void setUnresolvedRefs(int unresolvedRefs) {
		this.unresolvedRefs = unresolvedRefs;
	}

	/**
	 * @return the evaluated
	 */
	public boolean isEvaluated() {
		return evaluated;
	}

	/**
	 * @param evaluated the evaluated to set
	 */
	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

	/**
	 * @return the evaluatedValue
	 */
	public double getEvaluatedValue() {
		return evaluatedValue;
	}

	/**
	 * @param evaluatedValue the evaluatedValue to set
	 */
	public void setEvaluatedValue(double evaluatedValue) {
		this.evaluatedValue = evaluatedValue;
	}

	/**
	 * @return the workBook
	 */
	public WorkSheet getWorkBook() {
		return workBook;
	}

	/**
	 * @param workBook the workBook to set
	 */
	public void setWorkBook(WorkSheet workBook) {
		this.workBook = workBook;
	}

	/**
	 * @return the splitRegex
	 */
	public Pattern getSplitRegex() {
		return splitRegex;
	}

	/**
	 * @return the row
	 */
	public int getRowIndex() {
		return rowIndex;
	}

	/**
	 * @return the col
	 */
	public int getColumnIndex() {
		return columnIndex;
	}

	/**
	 * @return the references
	 */
	public List<CellReferenceToken> getCellReferencesTokens() {
		return cellReferencesTokens;
	}

	/**
	 * @return the tokenList
	 */
	public List<Token> getCellTokens() {
		return cellTokens;
	}

	/**
	 * @return the contents
	 */
	public String getContents() {
		return contents;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CellDetail cell = (CellDetail) o;

		if (columnIndex != cell.columnIndex) return false;
		if (rowIndex != cell.rowIndex) return false;
		if (!contents.equals(cell.contents)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return (String.valueOf(rowIndex) + String.valueOf(columnIndex)).hashCode();
	}
	
}

