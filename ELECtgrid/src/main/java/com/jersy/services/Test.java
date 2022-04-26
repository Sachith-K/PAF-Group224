package com.jersy.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/test")
public class Test {

	@Path("/show")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String show() {

		return "test the app";
	}

	@Path("/display")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String display() {

		return "display the app";
	}

}
