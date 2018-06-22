package com.challange.redmart_calculator.exception;

/**
 * Custom exception used to throw when there is circular dependency present.
 * ex: If Row A1 is depend on A2 and A2 is depend or A1 then throw this exception. 
 * 
 * @author manjkuma
 *
 */
public class CyclicDependencyException extends Exception {
	public CyclicDependencyException() { }

	public CyclicDependencyException(String message) {
		super(message);
	}

}
