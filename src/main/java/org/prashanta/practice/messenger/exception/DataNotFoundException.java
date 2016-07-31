package org.prashanta.practice.messenger.exception;

public class DataNotFoundException extends RuntimeException {
//  https://www.youtube.com/watch?v=9oeJc_VkZxo&index=27&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
	/**
	 * 
	 */
	private static final long serialVersionUID = -8641023454584478969L;

	public DataNotFoundException(String message){
		super(message);
	}
}
