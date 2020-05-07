package com.pin.sms.rest.bean;

import lombok.Data;

@Data
public class LoginResponse {

	private Long userId;
	private Boolean authenticated;
	private String username;
	private String message;
	private Boolean active;
	
}
