package br.com.bexs.travel.routes.processor.impl;

import static br.com.bexs.travel.routes.utility.FileUtility.CSV_SEPARATOR;

import java.net.URI;
import java.util.List;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.json.JSONObject;

import br.com.bexs.travel.routes.dto.RouteDTO;
import br.com.bexs.travel.routes.models.Route;
import br.com.bexs.travel.routes.processor.ProcessorObserver;
import br.com.bexs.travel.routes.processor.RouteProcessor;
import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;
import br.com.bexs.travel.routes.repository.RouteRepository;
import br.com.bexs.travel.routes.utility.FileUtility;
import br.com.bexs.travel.routes.utility.ValidationUtility;
import br.com.bexs.travel.routes.utility.exception.FileException;

public class WebProcessor extends RouteProcessor {

	private HttpServer server;

	public WebProcessor() {
		ProcessorObserver.getInstance().addProcessor(this);
	}

	@Override
	public void init() {

		ResourceConfig config = new ResourceConfig().packages("br.com.bexs.travel.routes.controller");
		URI uri = URI.create("http://localhost:8080/");
		this.server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Server is running"); //NOSONAR

	}

	public String get(String source, String destination) throws InvalidInputException {
		List<Integer> paths = calculatePath(source, destination);
		RouteDTO routeDTO = mapResult(paths);

		JSONObject json = new JSONObject();
		json.put("bestRoute", routeDTO.getRoute());
		json.put("cost", routeDTO.getCost());
		return json.toString();
	}

	public void createRoute(Route route) throws InvalidInputException, FileException {
		executeValidations(route);

		RouteRepository.getRoutes().add(route);
		String line = route.getSource() + CSV_SEPARATOR + route.getDestination() + CSV_SEPARATOR + route.getCost();
		FileUtility.writeLineToCSVFile(line);
	}

	@Override
	public void stop() {
		server.shutdownNow();
	}

	private void validateAirport(String airport, String fieldName) throws InvalidInputException {
		Integer initial = airportRepository.get(airport);
		if (initial == null) {
			String errorMessage = String.format("Field '%s 'is invalid. Accepted values %s", fieldName,
					airportRepository.getAirportInitials());
			throw new InvalidInputException("Bad Request", 400, errorMessage);
		}
	}

	private void executeValidations(Route route) throws InvalidInputException {

		if (RouteRepository.getRoutes().contains(route)) {
			throw new InvalidInputException("Conflict", 409, "A route with these parameters already exists.");
		}

		validateAirport(route.getSource(), "source");
		validateAirport(route.getDestination(), "destination");
		ValidationUtility.validateInput(route.getCost(), "Field cost can't be null");
	}

}
