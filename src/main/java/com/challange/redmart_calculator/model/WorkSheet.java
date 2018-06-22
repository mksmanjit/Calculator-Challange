package com.challange.redmart_calculator.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.challange.redmart_calculator.constant.SpreadsheetCalculatorConstants;

/**
 * Holds the detail for WorkBook.
 * 
 * @author manjkuma
 */
public class WorkSheet {
	/**
	 * Holds the list of CellDetail object whose value can be evaluated.
	 */
	private final List<CellDetail> resolvedCellDetails;
	/**
	 * Holds the list of CellDetail object depend on particular Cell.
	 */
	private final Map<Integer, HashSet<CellDetail>> dependenciesCellDetailMap;
	/**
	 * Holds the number of rows present in the workbook
	 */
	private int numberOfRows;
	/**
	 * Holds the number of columns present in the workbook.
	 */
	private int numberOfColumns;
	/**
	 * Holds the input value in the tabular format.
	 */
	private CellDetail[][] cellDetails;
	/**
	 * Total number of unsolvedCells in staring it is set to numberOfRows * numberOfColumns;
	 */
	private int unsolvedCells;
	/**
	 * True if workbook is cyclic dependent else false.
	 */
	boolean cyclicDependent;

	public WorkSheet() {
		resolvedCellDetails = new LinkedList<CellDetail>();
		dependenciesCellDetailMap = new HashMap<Integer, HashSet<CellDetail>>();
		setCyclicDependent(false);
	}
	
	/**
	 * @return the numberOfRows
	 */
	public int getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * @param numberOfRows the numberOfRows to set
	 */
	public void setNumberOfRows(int numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/**
	 * @return the numberOfColumns
	 */
	public int getNumberOfColumns() {
		return numberOfColumns;
	}

	/**
	 * @param numberOfColumns the numberOfColumns to set
	 */
	public void setNumberOfColumns(int numberOfColumns) {
		this.numberOfColumns = numberOfColumns;
	}

	/**
	 * @return the cellDetails
	 */
	public CellDetail[][] getCellDetails() {
		return cellDetails;
	}

	/**
	 * @param cellDetails the cellDetails to set
	 */
	public void setCellDetails(CellDetail[][] cellDetails) {
		this.cellDetails = cellDetails;
	}

	/**
	 * @return the unsolvedCells
	 */
	public int getUnsolvedCells() {
		return unsolvedCells;
	}

	/**
	 * @param unsolvedCells the unsolvedCells to set
	 */
	public void setUnsolvedCells(int unsolvedCells) {
		this.unsolvedCells = unsolvedCells;
	}

	/**
	 * @return the circularDependent
	 */
	public boolean isCyclicDependent() {
		return cyclicDependent;
	}

	/**
	 * @param circularDependent the circularDependent to set
	 */
	public void setCyclicDependent(boolean cyclicDependent) {
		this.cyclicDependent = cyclicDependent;
	}

	/**
	 * @return the resolvedCellDetails
	 */
	public List<CellDetail> getResolvedCellDetails() {
		return resolvedCellDetails;
	}

	/**
	 * @return the dependenciesMap
	 */
	public Map<Integer, HashSet<CellDetail>> getDependenciesCellDetailMap() {
		return dependenciesCellDetailMap;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(String.format("%d", numberOfColumns));
		stringBuilder.append(" ");
		stringBuilder.append(String.format("%d", numberOfRows));
		stringBuilder.append(System.lineSeparator());
		for (int i = 0; i < numberOfRows; i++) {
			for (int j = 0; j < numberOfColumns; j++) {
				CellDetail cellDetail = cellDetails[i][j];
				if (cellDetail.isEvaluated()) {
					String value = String.format("%.5f", cellDetail.getEvaluatedValue());
					stringBuilder.append(value);
				} else {
						stringBuilder.append(SpreadsheetCalculatorConstants.CYCLIC_DEPENDENCY_PRESENT);
				}
				if (i * j != (numberOfRows - 1) * (numberOfColumns - 1)) {
					stringBuilder.append(System.lineSeparator());
				}
			}
		}
		return stringBuilder.toString();
	}

}

