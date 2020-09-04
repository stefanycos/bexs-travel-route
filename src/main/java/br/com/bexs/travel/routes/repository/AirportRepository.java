package br.com.bexs.travel.routes.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AirportRepository {

	private static Map<String, Integer> airports = new HashMap<>();

	static {
		airports.put("GRU", 0);
		airports.put("BRC", 1);
		airports.put("SCL", 2);
		airports.put("CDG", 3);
		airports.put("ORL", 4);
	}

	public Map<String, Integer> getAirports() {
		return Collections.unmodifiableMap(airports);
	}

	public Integer get(String initial) {
		return airports.get(initial);
	}
	
	public List<String> getAirportInitials() {
		return new ArrayList<>(airports.keySet());
	}

}
