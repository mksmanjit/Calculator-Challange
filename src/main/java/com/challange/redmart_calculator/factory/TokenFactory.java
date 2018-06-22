package com.challange.redmart_calculator.factory;

import com.challange.redmart_calculator.constant.Operator;
import com.challange.redmart_calculator.expression.Token;
import com.challange.redmart_calculator.expression.CellReferenceToken;
import com.challange.redmart_calculator.expression.OperatorToken;
import com.challange.redmart_calculator.expression.ValueToken;

/**
 * This is the factory class for creating Token object..
 * @author manjkuma
 *
 */
public class TokenFactory {

    /**
     * This method is creating a token object based on the string passed.
     * 	
     * @param objectType name of the token type object you want to create.
     * @return the newly created object for type token
     * @throws RuntimeException If string is not valid then throw exception.
     */
	public Token createTokenObject(String objectType) throws RuntimeException {
		if (Operator.isOperatorSymbolPresent(objectType))
			return new OperatorToken(Operator.get(objectType));
		else if (objectType.matches(CellReferenceToken.cellRefRegex))
			return new CellReferenceToken(objectType);
		else if (objectType.matches(ValueToken.valueRegex))
			return new ValueToken(objectType);
		else
			throw new RuntimeException("Invalid object Type: " + objectType);
	}


}

