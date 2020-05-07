package com.pin.sms.rest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.hibernate.mapping.Map;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pin.sms.rest.utils.HashUtils;

@SpringBootTest
class PinSmsApiApplicationTests {

	@Test
	void contextLoads() {
	}
	
	public static void main(String[] args) throws JsonMappingException, JsonProcessingException {
		com.pin.sms.rest.utils.HashUtils HashUtils = new HashUtils();
		System.out.println(HashUtils.MD5("Tech2019@ptspl"));
		
		String json =  
				"  {\n" + 
				"    \"_id\": \"5eb31214d11d6b2af39016ed\",\n" + 
				"    \"index\": 0,\n" + 
				"    \"guid\": \"e38a0a56-baf6-4166-8b3b-98b9d800f7bc\",\n" + 
				"    \"isActive\": false,\n" + 
				"    \"balance\": \"$2,534.63\",\n" + 
				"    \"picture\": \"http://placehold.it/32x32\",\n" + 
				"    \"age\": 38,\n" + 
				"    \"eyeColor\": \"blue\",\n" + 
				"    \"name\": \"Nielsen Hubbard\",\n" + 
				"    \"gender\": \"male\",\n" + 
				"    \"company\": \"IMANT\",\n" + 
				"    \"email\": \"nielsenhubbard@imant.com\",\n" + 
				"    \"phone\": \"+1 (981) 438-2871\",\n" + 
				"    \"address\": \"714 Dekoven Court, Sparkill, Georgia, 5182\",\n" + 
				"    \"about\": \"Voluptate deserunt ut nisi sit incididunt proident reprehenderit excepteur dolore. Quis exercitation culpa magna minim labore ipsum ut amet nisi et adipisicing culpa ex. Velit ut in laboris eu incididunt irure officia enim dolore non veniam cillum labore ex. Mollit eu velit cupidatat eu deserunt aliquip eu esse ullamco dolore proident nisi. In do voluptate enim incididunt velit cillum exercitation dolor do id et. Ea do anim aliquip enim consequat eiusmod elit esse cupidatat elit consequat. Nulla quis aute amet eiusmod ex deserunt esse non tempor minim.\\r\\n\",\n" + 
				"    \"registered\": \"2015-10-25T11:44:33 -06:-30\",\n" + 
				"    \"latitude\": 8.14871,\n" + 
				"    \"longitude\": 95.645944,\n" + 
				"    \"tags\": [\n" + 
				"      \"magna\",\n" + 
				"      \"pariatur\",\n" + 
				"      \"irure\",\n" + 
				"      \"veniam\",\n" + 
				"      \"proident\",\n" + 
				"      \"ullamco\",\n" + 
				"      \"nulla\"\n" + 
				"    ],\n" + 
				"    \"friends\": [\n" + 
				"      {\n" + 
				"        \"id\": 0,\n" + 
				"        \"name\": \"Green Roach\"\n" + 
				"      },\n" + 
				"      {\n" + 
				"        \"id\": 1,\n" + 
				"        \"name\": \"Rebecca Newman\"\n" + 
				"      },\n" + 
				"      {\n" + 
				"        \"id\": 2,\n" + 
				"        \"name\": \"Callahan Holman\"\n" + 
				"      }\n" + 
				"    ],\n" + 
				"    \"greeting\": \"Hello, Nielsen Hubbard! You have 9 unread messages.\",\n" + 
				"    \"favoriteFruit\": \"apple\"\n" + 
				"  }";
		
		ObjectMapper mapper = new ObjectMapper();
		List<String> list = Arrays.asList(json, json, json, json, json,json, json, json, json, json, json, json, json, json, json);
		StopWatch stop = new StopWatch();
		stop.start();
		/*
		 * for(var i = 0 ; i < list.size() ; i++) {
		 * mapper.reader().forType(HashMap.class).readValue(list.get(i)); }
		 */
		//apper.reader().forType(List.class).readValue();
		//mapper.readValue(list);
		stop.stop();
		System.out.println("stop" + stop);
	}

}
