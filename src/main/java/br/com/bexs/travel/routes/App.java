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
					"Missing argument. A CSV file is required to initialize program." + filename);
			
			RouteRepository.createAirportsConnections(filename);
		} catch (final Exception e) {
			System.out.println(e.getMessage());
			System.exit(1);
		}

		initializeProcessors();

	}

	private static void initializeProcessors() {
		new WebProcessor();
		new CommandLineProcessor();
		ProcessorObserver.getInstance().initialize();
	}
}
