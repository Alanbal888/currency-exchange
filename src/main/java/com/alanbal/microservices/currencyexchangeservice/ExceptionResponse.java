package com.alanbal.microservices.currencyexchangeservice;

import java.util.Date;

public class ExceptionResponse {
	private Date timestamp;
	private String message;
	private String details;
	private String comment;
	
	
	public ExceptionResponse(Date timestamp, String message, String details, String comment) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
		this.comment = comment;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
}
