package com.challange.redmart_calculator.expression;

/**
 * Holds the actual value i.e numbers.
 * 
 * @author manjkuma
 *
 */
public class ValueToken extends Token {
	/**
	 * regex of matching number.
	 */
	public static final String valueRegex = "\\d+";
	
	/**
	 * Update the value in the parent class.
	 * @param str holds the value.
	 */
	public ValueToken(String str) {
		setToken(str);
	}

	/**
	 * @return the value in the double.
	 */
	public double getValue() {
		return Double.parseDouble(getToken());
	}
}

