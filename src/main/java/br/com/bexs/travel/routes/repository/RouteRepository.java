package br.com.bexs.travel.routes.repository;

import java.util.List;

import br.com.bexs.travel.routes.models.Route;
import br.com.bexs.travel.routes.processor.algorithm.Graph;
import br.com.bexs.travel.routes.utility.FileUtility;
import br.com.bexs.travel.routes.utility.exception.FileException;

public class RouteRepository {

	private static AirportRepository airportRepository = new AirportRepository();
	private static Graph connections;
	private static List<Route> routes;
	private static String csvFile;

	private RouteRepository() {
	}

	public static void createAirportsConnections(String filename) throws FileException {
		csvFile = filename;
		routes = FileUtility.readCSVFile(filename);

		Graph airportsConnections = new Graph(airportRepository.getAirports().size());

		for (Route route : routes) {
			int sourceIndex = airportRepository.get(route.getSource());
			int destinationIndex = airportRepository.get(route.getDestination());

			airportsConnections.makeEdge(sourceIndex, destinationIndex, route.getCost());
		}

		connections = airportsConnections;
	}

	public static Graph getConnections() {
		return connections;
	}

	public static List<Route> getRoutes() {
		return routes;
	}

	public static String getFilename() {
		return csvFile;
	}

}
