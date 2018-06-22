package com.challange.redmart_calculator.engine.impl;

import java.util.List;
import java.util.Stack;

import com.challange.redmart_calculator.constant.Operator;
import com.challange.redmart_calculator.engine.CellProcessing;
import com.challange.redmart_calculator.expression.Token;
import com.challange.redmart_calculator.expression.CellReferenceToken;
import com.challange.redmart_calculator.expression.OperatorToken;
import com.challange.redmart_calculator.expression.ValueToken;
import com.challange.redmart_calculator.model.CellDetail;

/**
 * This class processing the Cell in the RPN(Reverse Polish Notation).
 * 
 * @author manjkuma
 *
 */
public class RPNCellProcessingImpl implements CellProcessing {
	
	@Override
	public double evaluate(CellDetail curCell) throws RuntimeException {
		if (curCell.isEvaluated())
			return curCell.getEvaluatedValue();

		Stack<Double> rpnStack = new Stack<Double>();
		List<Token> curCellTokens = curCell.getCellTokens();

		for (Token token : curCellTokens) {
			if (token instanceof ValueToken) {
				rpnStack.push(((ValueToken) token).getValue());
			} else if (token instanceof CellReferenceToken) {
				CellReferenceToken refToken = (CellReferenceToken) token;
				CellDetail refCell = curCell.getWorkBook().getCellDetails()[refToken.getRefRow()][refToken.getRefCol()];
				rpnStack.push(evaluate(refCell));
			} else if (token instanceof OperatorToken) {
				OperatorToken operatorToken = (OperatorToken) token;
				rpnStack = applyOperator(operatorToken.getParsedValue(), rpnStack);
			} else {
				throw new RuntimeException("Error: Invalid token: " + token.toString());
			}
		}
		curCell.setEvaluatedValue(rpnStack.pop());
		curCell.setEvaluated(true);
		return curCell.getEvaluatedValue();
	}
	
	/**
	 * This method applying operator or operands and updating the rpnStack.
	 * 
	 * @param operator Operator constant.
	 * @param rpnStack stack holds all the token.
	 * @return  return the update rpnStack.
	 * @throws IllegalArgumentException throws if divide by zero error occured.
	 */
	private Stack<Double> applyOperator(Operator operator, Stack<Double> rpnStack) throws IllegalArgumentException {
		double op1 = rpnStack.pop();
		double op2 = rpnStack.pop();
		switch (operator) {
			case ADDITION:
				rpnStack.push(op2 + op1);
				break;
			case SUBSTRACTION:
				rpnStack.push(op2 - op1);
				break;
			case MULTIPLICATION:
				rpnStack.push(op2 * op1);
				break;
			case DIVISION:
				if (op1 == 0) {
					throw new IllegalArgumentException("Cannot divide by 0");
				}
				rpnStack.push(op2 / op1);
				break;
		}
		return rpnStack;
	}

}

