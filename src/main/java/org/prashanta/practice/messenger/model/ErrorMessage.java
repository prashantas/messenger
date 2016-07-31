package org.prashanta.practice.messenger.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement    // to convert this class instance to json/xml
public class ErrorMessage {
//on error handling ::  https://www.youtube.com/watch?v=9oeJc_VkZxo&index=27&list=PLqq-6Pq4lTTZh5U8RbdXq0WaYvZBz2rbn
		private String errorMessage;
		private int errorCode;
		private String documentation;
		public ErrorMessage(){}
		public ErrorMessage(String errorMessage, int errorCode, String documentation) {
			super();
			this.errorMessage = errorMessage;
			this.errorCode = errorCode;
			this.documentation = documentation;
		}
		public String getErrorMessage() {
			return errorMessage;
		}
		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
		public int getErrorCode() {
			return errorCode;
		}
		public void setErrorCode(int errorCode) {
			this.errorCode = errorCode;
		}
		public String getDocumentation() {
			return documentation;
		}
		public void setDocumentation(String documentation) {
			this.documentation = documentation;
		}
		
		
}
