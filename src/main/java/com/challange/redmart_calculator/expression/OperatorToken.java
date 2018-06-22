package com.challange.redmart_calculator.expression;

import com.challange.redmart_calculator.constant.Operator;

/**
 * Object for holding Operator.
 * 
 * @author manjkuma
 *
 */
public class OperatorToken extends Token {

	/**
	 * Update the value in parent class.
	 * 
	 * @param operator enum object of Operator.
	 */
	public OperatorToken(Operator operator) {
		setToken(operator.getOperatorSymbol());
	}

	/**
	 * @return return the operator object.
	 */
	public Operator getParsedValue() {
		return Operator.get(getToken());
	}

}


