package project.part8_services;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;

public class LoginFilter implements  ResourceFilter, 
									 ContainerRequestFilter,
									 ContainerResponseFilter{
	@Context
	private HttpServletRequest httpServletRequest;


	
	public ContainerRequest filter(ContainerRequest request) {
		HttpSession session = httpServletRequest.getSession(false);
		if(session == null) {
			String message = "In order to use the methods you must log on to the system";
			String json = "{\"error\": \"" + message + "\"}";
			ResponseBuilder builder = Response.status(HttpServletResponse.SC_UNAUTHORIZED).entity(json);
			throw new WebApplicationException(builder.build());
		}
		return request;
	}
	
	public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
		
		return response;
	}

	
	public ContainerRequestFilter getRequestFilter() {
		
		return this;
	}

	
	public ContainerResponseFilter getResponseFilter() {
		
		return this;
	}
	
	
	
	
}
