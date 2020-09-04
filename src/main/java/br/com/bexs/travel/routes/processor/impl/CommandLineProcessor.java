package br.com.bexs.travel.routes.processor.impl;

import java.util.List;
import java.util.Scanner;

import br.com.bexs.travel.routes.dto.RouteDTO;
import br.com.bexs.travel.routes.processor.ProcessorObserver;
import br.com.bexs.travel.routes.processor.RouteProcessor;
import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;

@SuppressWarnings({ CommandLineProcessor.SQUID_S2189, CommandLineProcessor.SQUID_S106 })
public class CommandLineProcessor extends RouteProcessor {

	public static final String SQUID_S106 = "squid:S106";
	public static final String SQUID_S2189 = "squid:S2189";

	public CommandLineProcessor() {
		ProcessorObserver.getInstance().addProcessor(this);
	}

	@SuppressWarnings("resource")
	@Override
	public void init() {
		Scanner in = new Scanner(System.in);

		printWelcomeMessage();

		while (true) {

			String message = "Best Route: ";

			System.out.println("Please, enter your route, e.g: GRU-CDG. Leave blank to exit.");

			String path = "";

			try {

				String line = in.nextLine().trim();

				verifyInput(line);

				String[] split = line.split("-");
				path = getBestRoute(split[0], split[1]);

			} catch (final Exception e) {
				message = "";
				System.out.println(e.getMessage());
			}

			System.out.println(message + path);
		}
	}

	@Override
	public void stop() {
		System.exit(0);
	}

	private void printWelcomeMessage() {
		System.out.println("Welcome to Travel Route System");
		System.out.println("-------------------------------------------------------");
	}

	private void verifyInput(String line) {
		if (line.isEmpty()) {
			ProcessorObserver.getInstance().stop();
		}
	}

	private String getBestRoute(String source, String destination) throws InvalidInputException {
		List<Integer> result = calculatePath(source, destination);
		RouteDTO routeDTO = mapResult(result);
		return routeDTO.getRoute() + " > $" + routeDTO.getCost();
	}

}
