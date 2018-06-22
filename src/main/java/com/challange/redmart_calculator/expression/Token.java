package com.challange.redmart_calculator.expression;

/**
 * This class holds the operand or operator or reference value.
 * 
 * @author manjkuma
 *
 */
public abstract class Token {

	/**
	 * Holds the operator, operand or reference value of a particular cell.
	 */
	private String token;

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	

}

