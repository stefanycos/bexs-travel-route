package br.com.bexs.travel.routes;

import org.junit.Test;

public class AppTest {

	@Test(expected = IndexOutOfBoundsException.class)
	public void whenStarting_givenNoArgument_thenThrowsIndexOutOfBoundsException() {
		App.main(null);
	}

	@Test(expected = Exception.class)
	public void whenStarting_givenEmpty_thenThrowsException() {
		String[] argument = new String[] { "" };
		App.main(argument);
	}
}
