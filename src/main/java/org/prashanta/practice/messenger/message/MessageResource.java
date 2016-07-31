package org.prashanta.practice.messenger.message;

import java.net.URI;
import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
//import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import org.prashanta.practice.messenger.message.beans.MessageFilterBean;
import org.prashanta.practice.messenger.model.Message;
import org.prashanta.practice.messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
// https://www.youtube.com/watch?v=vP9HU1o3zsE&index=31&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
//@Produces(value = { MediaType.APPLICATION_JSON, MediaType.TEXT_XML }) // supports both content type : Working
@Produces(MediaType.APPLICATION_JSON)
// if all the functions used these 2 annotations, we can put it before class
// and no need to put before every functions
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	/*@GET
	@Produces(MediaType.TEXT_XML)
	public List<Message> getMessage(){
		return messageService.getAllMessages();
	}*/

	/*   // commented to demonstrate BeanParam   :: Working
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getMessage(@QueryParam("year") int year,
									@QueryParam("start") int start,
									@QueryParam("size") int size){
									// add all possible query params
		if(year>0){
			// will be called as ::  http://localhost:8080/messenger/webapi/messages?year=2016
			// if we dont pass, then year will be zero
			return messageService.getAllMessagesForYear(year);
		}
		if(start>=0 && size>0){
			// will be called as : http://localhost:8080/messenger/webapi/messages?start=1&size=1
			return messageService.getAllMessagesPaginated(start, size);
		}
		return messageService.getAllMessages();
	}  
	
	*/
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Message> getJsonMessage(@BeanParam MessageFilterBean filterBean){
		
		System.out.println("Json message");
		// when the list of all possible query params is very big and it is
		// good practice to include each individual query params in the functions parameter.
		// In that case we can use @BeanParam. For that we have to define one bean, here 
		// we defined MessageFilterBean.
		if(filterBean.getYear()>0){
			// will be called as ::  http://localhost:8080/messenger/webapi/messages?year=2016
			// if we dont pass, then year will be zero
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 && filterBean.getSize()>0){
			// will be called as : http://localhost:8080/messenger/webapi/messages?start=1&size=1
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	
	@GET
	@Produces(MediaType.TEXT_XML)  // overrides the class level annotation
	public List<Message> getXmlMessage(@BeanParam MessageFilterBean filterBean){
		System.out.println("Xml message");
		// when the list of all possible query params is very big and it is
		// good practice to include each individual query params in the functions parameter.
		// In that case we can use @BeanParam. For that we have to define one bean, here 
		// we defined MessageFilterBean.
		if(filterBean.getYear()>0){
			// will be called as ::  http://localhost:8080/messenger/webapi/messages?year=2016
			// if we dont pass, then year will be zero
			return messageService.getAllMessagesForYear(filterBean.getYear());
		}
		if(filterBean.getStart()>=0 && filterBean.getSize()>0){
			// will be called as : http://localhost:8080/messenger/webapi/messages?start=1&size=1
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}
		return messageService.getAllMessages();
	}
	// https://www.youtube.com/watch?v=U4tOw0LxQW4&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=17
	@GET
	@Path("/test")
	@Produces(MediaType.TEXT_PLAIN)
	public String test(){
		return "Test";
	}
	
	@POST
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	/*public Message addMessage(Message message){
		return messageService.addMessage(message);
	}*/
	// sending status code
	public Response addMessage(Message message, @Context UriInfo uriInfo){
		//return messageService.addMessage(message);
		Message newMessage = messageService.addMessage(message);
		/*return Response.status(Status.CREATED)
						.entity(newMessage)
						.build();*/
		// location header : it is the URL of the newly created message
		// https://www.youtube.com/watch?v=HEabElNrfbo&index=26&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
		String newId = String.valueOf(newMessage.getId());
		URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build(); // this created new URL after appending newId to the current URI
		return Response.created(uri) // sends CREATED status i.e. 201 as well as location header
				.entity(newMessage)
				.build();
	}
	
	@PUT
	@Path("/{messageId}")  // messageId is variable
	//@Consumes(MediaType.APPLICATION_JSON)
	//@Produces(MediaType.APPLICATION_JSON)
	public Message updateMessage(@PathParam("messageId")long id,Message message){
		message.setId(id);
		return messageService.updateMessage(message);
	}
	
	@DELETE
	@Path("/{messageId}")  // messageId is variable
	//@Produces(MediaType.APPLICATION_JSON)
	public void deleteMessage(@PathParam("messageId")long id){
		// called as : http://localhost:8080/messenger/webapi/messages/1  with DELETE option 
		// here 1 is PathParam
		messageService.removeMessage(id);
	}
	
	// // https://www.youtube.com/watch?v=U4tOw0LxQW4&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=17
	
	@GET
	@Path("/{messageId}")  // messageId is variable
	//@Produces(MediaType.APPLICATION_JSON)
	/*public Message getMessage(@PathParam("messageId")long id){
		return messageService.getMessage(id);		
		
	}*/
	public Message getMessage(@PathParam("messageId")long id, @Context UriInfo uriInfo){
		Message message = messageService.getMessage(id);		
		//String uri = getUriForSelf(uriInfo, message);
		message.addLinks(getUriForSelf(uriInfo, message), "self");
		message.addLinks(getUriForProfile(uriInfo, message), "profile");
		message.addLinks(getUriForComments(uriInfo, message), "comments");
		
		return message;
	}
	
	private String getUriForComments(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)	
				.path(MessageResource.class, "getCommentResource")
				.path(CommentResource.class)
				.resolveTemplate("messageId", message.getId())
				.build()
				.toString();
	return uri;
	}
	// https://www.youtube.com/watch?v=dtO5NQ8K5Wo&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn&index=30
	private String getUriForProfile(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
					.path(ProfileResource.class)
					.path(message.getAuthor())
					.build()
					.toString();
		return uri;
	}
	private String getUriForSelf(UriInfo uriInfo, Message message) {
		String uri = uriInfo.getBaseUriBuilder()
					.path(MessageResource.class)
					.path(Long.toString(message.getId()))
					.build()
					.toString();
		return uri;
	}
	
	//below is for subresource
	
	//method type i.e. GET , POST,PUR oe DELETE is absent here
	// means for all type of requests it calls this below function and
	// is delegated to CommentResource
	
	@Path("/{messageId}/comments")
	/*public String test1(){
		return "test";
	}*/
	public CommentResource getCommentResource(){
		return new CommentResource();
	}
}
