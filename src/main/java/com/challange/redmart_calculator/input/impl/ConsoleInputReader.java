package com.challange.redmart_calculator.input.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.challange.redmart_calculator.expression.CellReferenceToken;
import com.challange.redmart_calculator.input.InputReader;
import com.challange.redmart_calculator.model.CellDetail;
import com.challange.redmart_calculator.model.WorkSheet;

/**
 * This class read the input from the console.
 * 
 * @author manjkuma
 *
 */
public class ConsoleInputReader implements InputReader {

	@Override
	public WorkSheet readInput() throws RuntimeException {
		Scanner inputScanner = new Scanner(System.in);
		WorkSheet workBook = new WorkSheet();
		int columns = inputScanner.nextInt();
		int rows = inputScanner.nextInt();
		workBook.setUnsolvedCells(rows * columns);
		workBook.setNumberOfColumns(columns);
		workBook.setNumberOfRows(rows);
		inputScanner.nextLine();

		CellDetail[][] cellDetails = new CellDetail[rows][columns];
		workBook.setCellDetails(cellDetails);

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				String data = inputScanner.nextLine().trim().toUpperCase();
				CellDetail curCell = cellDetails[row][col] = new CellDetail(row, col, data, workBook);
				if (curCell.getCellReferencesTokens().size() > 0) {
					addToDependenciesMap(curCell);
				} else {
					workBook.getResolvedCellDetails().add(curCell);
				}
			}
		}
		return workBook;
	}
	
	/**
	 * This method updating the dependenciesMap i.e. holds reference of those are depend on some other cell.
	 * 
	 * @param curCell holds the object of current CellDetail object.
	 */
	private void addToDependenciesMap(CellDetail curCell) {
		List<CellReferenceToken> references = curCell.getCellReferencesTokens();
		Map<Integer, HashSet<CellDetail>> dependnciesMap = curCell.getWorkBook().getDependenciesCellDetailMap();
		for (CellReferenceToken refToken : references) {
			HashSet<CellDetail> refBy;
			if (dependnciesMap.containsKey(refToken.hashCode())) {
				refBy = dependnciesMap.get(refToken.hashCode());
			} else {
				dependnciesMap.put(refToken.hashCode(), refBy = new HashSet<CellDetail>());
			}
			refBy.add(curCell);
		}
	}

}

