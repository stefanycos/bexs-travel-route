package br.com.bexs.travel.routes.processor.exeption;

public class InvalidInputException extends Exception {

	private static final long serialVersionUID = 1L;

	private String error;

	private Integer status;

	public InvalidInputException(String message) {
		super(message);
	}

	public InvalidInputException(String error, Integer status, String message) {
		super(message);
		this.error = error;
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public Integer getStatus() {
		return status;
	}

}
