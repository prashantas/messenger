package org.prashanta.practice.messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.prashanta.practice.messenger.database.DatabaseClass;
import org.prashanta.practice.messenger.model.Comment;
import org.prashanta.practice.messenger.model.ErrorMessage;
import org.prashanta.practice.messenger.model.Message;

public class CommentService {
// https://www.youtube.com/watch?v=O4dAxOCYAUg&index=25&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
		private Map<Long,Message>messages = DatabaseClass.getMessages();
		//public CommentService(){}
		
		/*public CommentService(){
		messages.put(1L, new Comment(1,"hellow world","Prashanta"));
		messages.put(2L, new Comment(2,"hellow jersey","Prashanta"));
		}*/
		public List<Comment> getAllComments(long messageId){
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			return new ArrayList<Comment>(comments.values());
		}
		
		public Comment getComment(long messageId,long commentId){
			ErrorMessage errorMessage1 = new ErrorMessage("Message Not Found",404,"http://javabrains.koushik.com");
			Response response= Response.status(Status.NOT_FOUND)
					.entity(errorMessage1)
					.build();					
			
			Message message = messages.get(messageId);
			if(message == null){
				throw new WebApplicationException(response);
			}
			
			
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			Comment comment = comments.get(commentId);
			ErrorMessage errorMessage2 = new ErrorMessage("Comment Not Found",404,"http://javabrains.koushik.com");
			 response= Response.status(Status.NOT_FOUND)
					.entity(errorMessage2)
					.build();	
			if(comment == null){
				throw new NotFoundException(response);
			}
			return comment;
			
			/*Map<Long,Comment> comments = messages.get(messageId).getComments();
			return comments.get(commentId);*/
		}
		
		public Comment addComment(long messageId,Comment comment){
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			comment.setId(comments.size()+1);
			comments.put(comment.getId(), comment);
			return comment;
			
		}
		
		public Comment updateComment(long messageId,Comment comment){
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			if(comment.getId()<=0){
				return null;
			}
			
			comments.put(comment.getId(), comment);
			return comment;
		}
		
		public Comment removeComment(long messageId,long commentId){
			Map<Long,Comment> comments = messages.get(messageId).getComments();
			return comments.remove(commentId);
		}
		
}
