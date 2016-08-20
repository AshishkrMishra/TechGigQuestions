package com.techgig.tautology;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CandidateCode {

	static final String YES = "Yes";
	static final String NO = "No";
	static final String INVALID = "Invalid";
	static final String AND = "A";
	static final String OR = "O";
	static final String IMPLIES = "I";
	static final String IFONLYIF = "F";
	static final String NOT = "N";
	static final String UNIOPERATOR = "UNIOPERATOR";
	static final String BINARYOPERATOR = "BINARYOPERATOR";
	static final String OPERAND = "OPERAND";

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String expression = "NANpp";
		

	}

	public static String getType(String character) {
		if (character.equals(NOT)) {
			return UNIOPERATOR;
		} else if (character.equals(AND) || character.equals(OR)
				|| character.equals(IMPLIES) || character.equals(IFONLYIF)) {
			return BINARYOPERATOR;
		}
		return OPERAND;

	}

	public static boolean uniEvalute(String operandOne) {
		return !new Boolean(operandOne);
	}

	public static boolean evalute(String operandOne, String operand,
			String operandTwo) {
		boolean result = false;
		if (operand.equals(AND)) {
			return new Boolean(operandOne) && new Boolean(operandTwo);
		} else if (operand.equals(OR)) {
			return new Boolean(operandOne) || new Boolean(operandTwo);
		} else if (operand.equals(IMPLIES)) {
			return evalute(new Boolean(!uniEvalute(operandOne)).toString(), OR,
					operandTwo);
		} else {
			return evalute(
					new Boolean(evalute(operandOne, IMPLIES, operandTwo))
							.toString(), AND, new Boolean(evalute(operandOne,
							IMPLIES, operandTwo)).toString());
		}
		// (operand.equals(IFONLYIF))

	}

	public static boolean isValidExpression(String expression) {
		boolean result = false;

		return result;

	}

	public static boolean checkisPureString(String expression) {
		String regex = "[a-zA-Z]+";
		return expression.matches(regex);

	}

}
