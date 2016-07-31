package org.prashanta.practice.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.prashanta.practice.messenger.model.ErrorMessage;

// commenting/remover  @Provider means we are not registering
// JAX-RS provides WebApplicationException. We'll use that
// commenting it  @Provider // registers it in jax-rs
// this is generic exception called in all cases
public class  GenericExceptionMapper implements ExceptionMapper<Throwable> {

	// http://stackoverflow.com/questions/33386225/jersey-exceptionmapper-not-being-invoked
	@Override
	public Response toResponse(Throwable ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),500,"http://javabrains.koushik.com");
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
				
	}

}