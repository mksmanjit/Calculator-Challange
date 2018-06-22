package com.challange.redmart_calculator.engine;

import com.challange.redmart_calculator.exception.CyclicDependencyException;
import com.challange.redmart_calculator.model.WorkSheet;

/**
 * This class is doing processing at the work sheet level.
 * @author manjkuma
 *
 */
public interface WorksheetProcessing {
	
	/**
	 * This method doing processing at the work sheet level and internally calling CellProcessing
	 * and after successfully processing resolve dependent dependencies.
	 * 
	 * @param workBook  holds the object for the workBook
	 * @throws CyclicDependencyException If there is circular dependency present then throw
	 *         CircularDependencyException ex. A is depend on B and B is depend on A.
	 * 
	 */
	public void processWorksheet(WorkSheet workBook) throws CyclicDependencyException;

}
