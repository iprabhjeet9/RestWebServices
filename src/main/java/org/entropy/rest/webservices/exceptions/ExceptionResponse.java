package org.entropy.rest.webservices.exceptions;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String detail;
	private String message;
	
	public ExceptionResponse(Date timestamp, String detail, String message) {
		super();
		this.timestamp = timestamp;
		this.detail = detail;
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
