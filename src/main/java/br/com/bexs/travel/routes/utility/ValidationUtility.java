package br.com.bexs.travel.routes.utility;

import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;

public class ValidationUtility {

	private ValidationUtility() {

	}

	public static void validateInput(String text, String errorMessage) throws InvalidInputException {
		if (text == null || text.isEmpty()) {
			throw new InvalidInputException(errorMessage);
		}
	}

	public static void validateInput(Integer value, String errorMessage) throws InvalidInputException {
		if (value == null) {
			throw new InvalidInputException(errorMessage);
		}
	}

}
