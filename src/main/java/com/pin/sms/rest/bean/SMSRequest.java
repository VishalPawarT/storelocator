package com.pin.sms.rest.bean;

import java.util.Date;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SMSRequest {

	private String username;
	private String password;
	private String smsservicetype;
	private String content;
	
	private String bulkmobno;
	private String msgid;
	private String senderid;

	//Internal purpose variables
	private String messageType;
	private Long totalMessageCount;
	private Double messageUnit;
	private String[] mobileNumberList;
	private Date messageDate = new Date();
	
	private Long memId;
	private Set<String> memPrivSet;
	private Integer accountPriority;
	private Double basePrice;
	private Double smsRate;
	private Long refId;
}
