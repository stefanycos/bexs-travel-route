package br.com.bexs.travel.routes.processor;

import java.util.List;

import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;

public interface ExecutorProcessor {

	void init();

	List<Integer> calculatePath(String source, String destination) throws InvalidInputException;

	void stop();

}
