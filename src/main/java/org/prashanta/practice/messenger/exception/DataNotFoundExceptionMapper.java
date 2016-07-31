package org.prashanta.practice.messenger.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.prashanta.practice.messenger.model.ErrorMessage;

@Provider // registers it in jax-rs
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	// http://stackoverflow.com/questions/33386225/jersey-exceptionmapper-not-being-invoked
	@Override
	public Response toResponse(DataNotFoundException ex) {
		// TODO Auto-generated method stub
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(),404,"http://javabrains.koushik.com");
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
				
	}

}
