package br.com.bexs.travel.routes.processor;

import java.util.ArrayList;
import java.util.List;

public class ProcessorObserver {

	private static ProcessorObserver intance;

	private List<ExecutorProcessor> processors = new ArrayList<>();

	public void addProcessor(ExecutorProcessor processor) {
		this.processors.add(processor);
	}

	public void initialize() {
		for (ExecutorProcessor processor : processors) {
			processor.init();
		}
	}

	public void stop() {
		for (ExecutorProcessor processor : processors) {
			processor.stop();
		}
	}

	public static ProcessorObserver getInstance() {
		if (intance == null) {
			intance = new ProcessorObserver();
		}

		return intance;
	}

}
