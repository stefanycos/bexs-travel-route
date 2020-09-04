package br.com.bexs.travel.routes.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.com.bexs.travel.routes.dto.ErrorResponseDTO;
import br.com.bexs.travel.routes.models.Route;
import br.com.bexs.travel.routes.processor.exeption.InvalidInputException;
import br.com.bexs.travel.routes.processor.impl.WebProcessor;
import br.com.bexs.travel.routes.utility.exception.FileException;

@Path("routes")
public class RoutesController {

	private WebProcessor processor = new WebProcessor();

	@GET
	@Path("/{source}/{destination}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("source") String source, @PathParam("destination") String destination) {
		try {

			String entity = processor.get(source, destination); //@formatter:off
			return Response.status(Status.OK) 
					.entity(entity)
					.build();

		} catch (final Exception e) {
			String errorMessage = ErrorResponseDTO.buildErrorJSON(e.getMessage(), "Bad Request");
			return Response.status(Status.BAD_REQUEST)
					.entity(errorMessage)
					.build(); //@formatter:on
		}

	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response create(Route route) { 
		try {
			processor.createRoute(route);
			return Response.status(Status.CREATED).build();
			
		} catch (final InvalidInputException e) { //@formatter:off
			
			String errorMessage = ErrorResponseDTO.buildErrorJSON(e.getMessage(), e.getError());
			return Response.status(e.getStatus())
					.entity(errorMessage)
					.build(); 
			
		} catch (final FileException e) {
			
			String errorMessage = ErrorResponseDTO.buildErrorJSON(e.getMessage(), "Internal Error");
			return Response.status(Status.INTERNAL_SERVER_ERROR)
					.entity(errorMessage)
					.build(); //@formatter:on
		}
	}

}
