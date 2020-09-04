package br.com.bexs.travel.routes.processor;

import java.util.List;
import java.util.Map.Entry;

import br.com.bexs.travel.routes.dto.RouteDTO;
import br.com.bexs.travel.routes.models.Route;
import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;
import br.com.bexs.travel.routes.repository.AirportRepository;
import br.com.bexs.travel.routes.repository.RouteRepository;

public abstract class RouteProcessor implements ExecutorProcessor {

	protected AirportRepository airportRepository = new AirportRepository();

	@Override
	public List<Integer> calculatePath(String source, String destination) throws InvalidInputException {

		Integer airportSource = airportRepository.getAirports().get(source);
		Integer airportDestination = airportRepository.getAirports().get(destination);
		this.validateAirports(airportSource, airportDestination);

		return RouteRepository.getConnections().path(airportSource, airportDestination);

	}

	protected RouteDTO mapResult(List<Integer> paths) {
		StringBuilder builder = new StringBuilder();

		int cost = 0;

		for (int i = 0; i < paths.size(); i++) {

			int indexSource = paths.get(i);

			if (isLastItem(paths, i)) {
				builder.append(getByKey(indexSource));
				break;
			}

			int indexDestination = paths.get(i + 1);

			String source = getByKey(indexSource);
			String destination = getByKey(indexDestination);

			cost += getCost(source, destination);

			builder.append(source).append(" - ");
		}

		return new RouteDTO(builder.toString(), cost);
	}

	protected String getByKey(Integer index) {
		for (Entry<String, Integer> airport : airportRepository.getAirports().entrySet()) {
			if (airport.getValue().equals(index)) {
				return airport.getKey();
			}
		}

		return null;
	}

	private boolean isLastItem(List<Integer> paths, int i) {
		return i + 1 == paths.size();
	}

	private int getCost(String source, String destination) {

		for (Route route : RouteRepository.getRoutes()) {
			if (route.getSource().equals(source) && route.getDestination().equals(destination)) {

				return route.getCost();
			}
		}

		return 0;
	}

	private void validateAirports(Integer source, Integer destination) throws InvalidInputException {
		if (source == null) {
			throw new InvalidInputException(
					"Invalid airport source. Accepted values " + airportRepository.getAirportInitials());

		}

		if (destination == null) {
			throw new InvalidInputException(
					"Invalid airport destination. Accepted values " + airportRepository.getAirportInitials());

		}
	}

}
