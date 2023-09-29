package com.mayuratech.api.payload;

import java.util.Date;

public class ErrorDetails {
	private Date timeStamp;
	private String title;
	private String details;
	
	public Date getTimeStamp() {
		return timeStamp;
	}

	public String getTitle() {
		return title;
	}

	public String getDetails() {
		return details;
	}

	public ErrorDetails(Date timeStamp, String title, String details) {
		this.timeStamp = timeStamp;
		this.title = title;
		this.details = details;
	}
	
	
}
