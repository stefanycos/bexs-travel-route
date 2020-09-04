package br.com.bexs.travel.routes.utils;

import java.io.File;
import java.io.IOException;

public class TestUtils {

	public String getFilename() throws IOException {

		String fileName = "input-routes.csv";
		ClassLoader classLoader = getClass().getClassLoader();

		File file = new File(classLoader.getResource(fileName).getFile());
		return file.getAbsolutePath();

	}

}
