package br.com.bexs.travel.routes.utility;

import org.junit.Test;

import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;

public class ValidationsUtility {

	@Test(expected = InvalidInputException.class)
	public void givenAText_thenThrowsException_whenInvalid() throws InvalidInputException {
		ValidationUtility.validateInput("", "Can't be empty");
	}

}
