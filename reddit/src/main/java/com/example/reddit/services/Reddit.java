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
		httpHeaders.setBasicAuth(this.apiKey, this.apiSecret);
		httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		httpHeaders.set("User-Agent", "reddit-parser");
		String body = "grant_type=client_credentials";

		HttpEntity<String> request = new HttpEntity<>(body, httpHeaders);

		ResponseEntity<String> response = restTemplate.exchange(this.oauthURL, HttpMethod.POST, request, String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		String token = "";
		try {
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			token = jsonNode.path("access_token").asText();
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
		//
		return token;
	}
	
	public List<Post> getResults(String token, String subreddit) throws JsonMappingException, JsonProcessingException {

		String url = this.searchURL + subreddit + "/hot";
		token = "Bearer " + token;
		
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "reddit-parser");  
		// attach token to the request
		headers.set("Authorization", token);
		HttpEntity<String> httpEntity = new HttpEntity<>(headers);
		//

		// make call to retrieve list of posts
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);
		//

		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = objectMapper.readTree(response.getBody());

		List<Post> listOfPosts = new ArrayList<>();

		for (int i = 0; i < 10; i++) {
			String title = jsonNode.path("data").path("children").path(i).path("data").path("title").asText();
			String ups = jsonNode.path("data").path("children").path(i).path("data").path("ups").asText();
			String image = jsonNode.path("data").path("children").path(i).path("data").path("url").asText();

			Post post = new Post(title, Integer.parseInt(ups), image);
			listOfPosts.add(post);
		}

		return listOfPosts;
	}

}
