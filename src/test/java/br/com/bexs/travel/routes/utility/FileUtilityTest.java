package br.com.bexs.travel.routes.utility;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import br.com.bexs.travel.routes.models.Route;
import br.com.bexs.travel.routes.utils.TestUtils;

public class FileUtilityTest {

	private TestUtils utils = new TestUtils();

	@Test
	public void givenAFileName_thenReadAndGetRoutes() throws Exception {
		String filename = utils.getFilename();
		List<Route> routes = FileUtility.readCSVFile(filename);

		Assert.assertTrue(!routes.isEmpty());
		Assert.assertEquals(routes.get(0).getSource(), "GRU");

	}

}
