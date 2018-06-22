package com.challange.redmart_calculator.engine.impl;

import java.util.List;
import java.util.Set;

import com.challange.redmart_calculator.engine.CellProcessing;
import com.challange.redmart_calculator.engine.WorksheetProcessing;
import com.challange.redmart_calculator.exception.CyclicDependencyException;
import com.challange.redmart_calculator.model.CellDetail;
import com.challange.redmart_calculator.model.WorkSheet;

public class WorksheetProcessingImpl implements WorksheetProcessing {
	
	/**
	 * Holds object for doing Cell wise processing.
	 */
	CellProcessing cellProcessing;
	
	/**
	 * Inject dependencies during object creation.
	 *  
	 * @param cellProcessing Holds object for doing Cell wise processing.
	 */
	public WorksheetProcessingImpl(CellProcessing cellProcessing) {
		this.cellProcessing = cellProcessing;
	}
	
	@Override
	public void processWorksheet(WorkSheet workBook) throws CyclicDependencyException {
		List<CellDetail> resolvedCellDetails = workBook.getResolvedCellDetails();	
		while (resolvedCellDetails.size() > 0) {
			CellDetail curCell = resolvedCellDetails.remove(0);
			cellProcessing.evaluate(curCell);
			workBook.setUnsolvedCells(workBook.getUnsolvedCells() - 1);
			addResolvedCellToResolvedCellDetailList(curCell);
		}
		if (workBook.getUnsolvedCells() != 0) {
			workBook.setCyclicDependent(true);
			throw new CyclicDependencyException("CircularDependency present in this workbook.");
		}
	}
	
	/**
	 * This method resolved cell which are depend on this current cell.
	 * 
	 * @param curCell current processed CellDetail.
	 */
	private void addResolvedCellToResolvedCellDetailList(CellDetail curCell) {
		if (curCell.getWorkBook().getDependenciesCellDetailMap().containsKey(curCell.hashCode())) {
			Set<CellDetail> curCellDeps = curCell.getWorkBook().getDependenciesCellDetailMap().get(curCell.hashCode());
			for (CellDetail depCell : curCellDeps) {
				depCell.setUnresolvedRefs(depCell.getUnresolvedRefs() - 1);
				if (depCell.getUnresolvedRefs() == 0) {
					curCell.getWorkBook().getResolvedCellDetails().add(depCell);
				}
			}
		}
	}

}

