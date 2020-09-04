package br.com.bexs.travel.routes.utility;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import br.com.bexs.travel.routes.models.Route;
import br.com.bexs.travel.routes.repository.RouteRepository;
import br.com.bexs.travel.routes.utility.exception.FileException;

public class FileUtility {

	private static final String ERROR_MESSAGE = "Error when trying to read file ";
	public static final String CSV_SEPARATOR = ",";

	private FileUtility() {
	}

	public static List<Route> readCSVFile(String filename) throws FileException {

		String line = "";

		List<Route> routes = new ArrayList<>();

		try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

			while ((line = br.readLine()) != null) {
				String[] values = line.split(CSV_SEPARATOR);
				routes.add(new Route(values[0], values[1], Integer.parseInt(values[2])));
			}

			return routes;
		} catch (final Exception e) {
			throw new FileException(ERROR_MESSAGE + filename);
		}

	}

	public static void writeLineToCSVFile(String line) throws FileException {

		String filename = RouteRepository.getFilename();

		try ( //@formatter:off
				FileWriter fstream = new FileWriter(filename, true);
				BufferedWriter out = new BufferedWriter(fstream);
		) { //@formatter:on
			out.write(line);
			out.newLine();
		}

		catch (final Exception e) {
			throw new FileException(ERROR_MESSAGE + filename);
		}

	}
}
