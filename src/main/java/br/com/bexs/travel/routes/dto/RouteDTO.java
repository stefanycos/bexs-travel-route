package br.com.bexs.travel.routes.dto;

public class RouteDTO {

	private String route;

	private Integer cost;

	public RouteDTO() {
	}

	public RouteDTO(String route, Integer cost) {
		this.route = route;
		this.cost = cost;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public Integer getCost() {
		return cost;
	}

	public void setCost(Integer cost) {
		this.cost = cost;
	}

}
