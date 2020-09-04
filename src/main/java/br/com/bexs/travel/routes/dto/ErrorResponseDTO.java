package br.com.bexs.travel.routes.dto;

import org.json.JSONObject;

public class ErrorResponseDTO {

	private ErrorResponseDTO() {

	}

	public static String buildErrorJSON(String message, String error) {
		JSONObject json = new JSONObject();
		json.put("error", error);
		json.put("message", message);
		return json.toString();
	}
}
