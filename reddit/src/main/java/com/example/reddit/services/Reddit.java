package com.example.reddit.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.reddit.model.Post;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class Reddit {
	
	@Value("${reddit.api.key}")
	String apiKey;
	
	@Value("${reddit.api.secret}")
	String apiSecret;

	//https://www.reddit.com/api/v1/access_token
	@Value("${reddit.api.oauth.url}")
	String oauthURL;

	//https://oauth.reddit.com/r/
	@Value("${reddit.api.search.url}")
	String searchURL;
	
	
	public String getToken() {
		
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders httpHeaders = new HttpHeaders();
		// make call to token endpoint and retrieve token



		//
		return null;
	}
	
	public List<Post> getResults(String token, String subreddit) throws JsonMappingException, JsonProcessingException {

		String url = this.searchURL + subreddit + "/hot";
		token = "Bearer " + token;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "reddit-parser");  
		// attach token to the request

		//

		// make call to retrieve list of posts

		//

		return null;
	}

}
