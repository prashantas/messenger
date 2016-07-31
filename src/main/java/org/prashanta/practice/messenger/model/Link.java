package org.prashanta.practice.messenger.model;
// HATEOAS
// https://www.youtube.com/watch?v=Mp6LpIg7h84&index=29&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
public class Link {
	private String link;
	private String rel;
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	
}
