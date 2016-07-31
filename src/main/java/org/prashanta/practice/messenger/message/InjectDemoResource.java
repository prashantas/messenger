package org.prashanta.practice.messenger.message;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {

	@GET
	@Path("annotations")
	public String getParamsUsingAnnotations(@MatrixParam("param") String matrixParam,
											@HeaderParam("customHeader") String header,
											@CookieParam("name") String cookie){
		// called as : http://localhost:8080/messenger/webapi/injectdemo/annotations;param=value54
		// here param is Matrix Param . This is another way of sending parameters . Its like Query
		// parameters
		// header params need to be sent in header
		return "Matrix Param: " + matrixParam +"::Header param:" + header +"::Cookie:"+cookie; 
	}
	
	//@FormParam
	// when Form submitted, it goes like key,val pairs
	// to capture those @FormParam is used. Not widely used
	//#############################################################################
	
	@GET
	@Path("context")  // @Context can be used to get all the paramentes, headers, cookies 
	public String getParamsUsingContext(@Context UriInfo uriInfo,@Context HttpHeaders headers){
		// called as : http://localhost:8080/messenger/webapi/injectdemo/context
		 String path = uriInfo.getAbsolutePath().toString();
		 String cookies = headers.getCookies().toString();
		return "Path:" + path + "::Cookies:" + cookies;
	}
}
