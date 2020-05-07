package com.pin.sms.rest.response;

public enum ResponseTypes {
	SUCCESS(200,"success"),
	BAD_REQUEST(400,"Bad Request"),
	UNAVAILABLE(503,"temporarily unavailable"),
	INTERNAL_ERROR(500,"Internal Server Error");
	
	private int code;
	private String status;
	
	private ResponseTypes(int code,String status) {
		this.code=code;
		this.status=status;
	}

	public int getCode() {
		return code;
	}

	public String getStatus() {
		return status;
	}
	
}