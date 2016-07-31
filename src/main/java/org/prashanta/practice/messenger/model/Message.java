package org.prashanta.practice.messenger.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement    // conversts Message to XML
public class Message {

		private long id;
		private String message;
		private Date created;
		private String author;
		private Map<Long,Comment> comments = new HashMap<>();
		private List<Link> links =  new ArrayList<>();
		
		public Message(){}
		public Message(long id, String message, String author) {
			super();
			this.id = id;
			this.message = message;
			this.created = new Date();
			this.author = author;
		}
		public long getId() {
			return id;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public List<Link> getLinks() {
			return links;
		}
		public void setLinks(List<Link> links) {
			this.links = links;
		}
		public Date getCreated() {
			return created;
		}
		public void setCreated(Date created) {
			this.created = created;
		}
		public String getAuthor() {
			return author;
		}
		public void setAuthor(String author) {
			this.author = author;
		}
		
		@XmlTransient   // don't want to Comments data to show up when message instance is pulled up in the API
		// i.e. don't want the list of all comments when we access a Message
		// i.e. comment list to be ignored when message is converted to XML or JSON
		public Map<Long, Comment> getComments() {
			return comments;
		}
		public void setComments(Map<Long, Comment> comments) {
			this.comments = comments;
		}
		
		// https://www.youtube.com/watch?v=Mp6LpIg7h84&index=29&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
		public void addLinks(String url, String rel){
			Link link = new Link();
			link.setLink(url);
			link.setRel(rel);
			links.add(link);
		}
}
