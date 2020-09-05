package br.com.bexs.travel.routes;

import br.com.bexs.travel.routes.processor.ProcessorObserver;
import br.com.bexs.travel.routes.processor.impl.CommandLineProcessor;
import br.com.bexs.travel.routes.processor.impl.WebProcessor;
import br.com.bexs.travel.routes.repository.RouteRepository;
import br.com.bexs.travel.routes.utility.ValidationUtility;

public class App {

	public static void main(String[] args) {

		try {
			String filename = args[0];
			ValidationUtility.validateInput(filename,
					"Invalid argument. A CSV file is required to initialize program." + filename);

			RouteRepository.createAirportsConnections(filename);
		} catch (final IndexOutOfBoundsException e) {
			printErrorMessageAndExit("Missing argument. A CSV file is required to initialize program.");
		} catch (final Exception e) {
			printErrorMessageAndExit(e.getMessage());
		}

		initializeProcessors();

	}

	private static void initializeProcessors() {
		new WebProcessor();
		new CommandLineProcessor();
		ProcessorObserver.getInstance().initialize();
	}

	private static void printErrorMessageAndExit(String message) {
		System.out.println(message); // NOSONAR
		System.exit(1);
	}
}
