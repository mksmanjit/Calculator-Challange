package com.challange.redmart_calculator.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * This enum holds the constant for operator used.
 * 
 * @author manjkuma
 *
 */
public enum Operator {


	ADDITION("+"), SUBSTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");

	/**
	 * Holds key as a String symbol of operator and value as Operator object.
	 */
	private static final Map<String, Operator> operatorMap;

	// load all the operator into the map during class load.
	static {
		operatorMap = Arrays.stream(Operator.values())
				.collect(Collectors.toMap((operator) -> operator.getOperatorSymbol(), (operator) -> operator));
	}

	/**
	 * Holds the operator symbol.
	 */
	private final String operatorSymbol;

	/**
	 * constructor for setting the operator symbol.
	 * 
	 * @param symbol  operator symbol.
	 */
	private Operator(String symbol) {
		operatorSymbol = symbol;
	}

	/**
	 * @param symbol operator symbol.
	 * @return Return the operator object for the given symbol else null.
	 */
	public static Operator get(String symbol) {
		return operatorMap.get(symbol);
	}

	/**
	 * 
	 * @param symbol operator symbol.
	 * @return return true if symbol is present; false if not present.
	 */
	public static boolean isOperatorSymbolPresent(String symbol) {
		return get(symbol) != null;
	}

	/**
	 * @return return the operator symbol.
	 */
	public String getOperatorSymbol() {
		return operatorSymbol;
	}
}

