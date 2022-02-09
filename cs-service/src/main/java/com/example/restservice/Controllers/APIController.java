package com.example.restservice.Controllers;

import org.json.simple.JSONObject;
import org.springframework.web.client.RestTemplate;

//Third party API example using a rest template
public class APIController {
	
	static void SendRequestExternal(String body, String callback) {
		try {
		String url = "http://example.com/request";
		JSONObject content = new JSONObject();
		content.put("body", body);
		content.put("callback", callback);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, content);
	  } catch(Exception e) {
		  //We can error handle if the service fails and throws an exception
	  }
	}
	
}