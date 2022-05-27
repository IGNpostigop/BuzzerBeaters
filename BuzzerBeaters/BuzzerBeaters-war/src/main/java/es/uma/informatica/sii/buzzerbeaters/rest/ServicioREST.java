package es.uma.informatica.sii.buzzerbeaters.rest;

import java.net.URI;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import negocioEjb.GestionClientes;
import negocioEjb.GestionCuentas;



@Path("")
public class ServicioREST {
	@EJB
	private GestionClientes clienteEjb;
	
	@EJB
	private GestionCuentas cuentasEjb;
	
	@Context
	private UriInfo uriInfo;
	
	@HeaderParam("User-auth")
	private String autorizacion;
	
	
	
	
	
}
