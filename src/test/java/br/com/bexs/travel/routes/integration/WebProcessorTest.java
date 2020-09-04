package br.com.bexs.travel.routes.integration;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import br.com.bexs.travel.routes.models.Route;

public class WebProcessorTest {

	private static WebTarget target;
	private static final String PATH = "/routes";

	@BeforeClass
	public static void setUp() {

		Client client = ClientBuilder.newClient();
		target = client.target("http://localhost:8080");
	}

	@Test
	public void get_givenSourceAndDestination_thenReturnBestRoute() {
		Response response = target.path(PATH + "/GRU/CDG").request().get(Response.class);

		String content = response.readEntity(String.class);
		Assert.assertTrue(content.contains("GRU - BRC - SCL - ORL - CDG"));
		Assert.assertTrue(content.contains("40"));
	}

	@Test
	public void get_givenInvalidSource_thenReturnBadRequest() {
		Response response = target.path(PATH + "/GG/CDG").request().get(Response.class);

		String content = response.readEntity(String.class);
		Assert.assertTrue(content.contains("Invalid airport source. Accepted values [BRC, CDG, ORL, GRU, SCL]"));
		Assert.assertTrue(content.contains("Bad Request"));
	}

	@Test
	public void create_givenRoute_thenReturnSuccess() { //@formatter:off
		Response response = target.path(PATH) 
				.request()
				.post(Entity.entity(createRoute("ORL", "BRC"), MediaType.APPLICATION_JSON)); //@formatter:on

		Assert.assertEquals(response.getStatus(), 201);
	}

	@Test
	public void createRoute_givenAnInvalidBody_thenReturnBadRequest() { //@formatter:off
		Response response = target.path(PATH) 
				.request()
				.post(Entity.entity(createRoute("ORL", ""), MediaType.APPLICATION_JSON));

		String content = response.readEntity(String.class);
		Assert.assertTrue(content.contains("Field 'destination 'is invalid. Accepted values"));
		Assert.assertTrue(content.contains("Bad Request")); //@formatter:on
	}

	@Test
	public void createRoute_givenExistentRoute_thenReturnConflict() { //@formatter:off
		Response response = target.path(PATH) 
				.request()
				.post(Entity.entity(createRoute("GRU", "CDG"), MediaType.APPLICATION_JSON));

		String content = response.readEntity(String.class);
		Assert.assertTrue(content.contains("A route with these parameters already exists."));
		Assert.assertTrue(content.contains("Conflict")); //@formatter:on
	}

	private Route createRoute(String source, String destination) {
		return new Route(source, destination, 33);
	}
}
