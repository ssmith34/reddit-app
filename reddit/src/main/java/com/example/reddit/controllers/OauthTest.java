package com.example.reddit.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.reddit.model.Post;
import com.example.reddit.services.Reddit;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin
@RestController
public class OauthTest {

	@Autowired
	Reddit redditService;
	
	
	@RequestMapping(path="/test", method=RequestMethod.GET)
	public List<Post> test(@RequestParam String subreddit) throws JsonMappingException, JsonProcessingException  {
	
		String token = redditService.getToken();		   
	    return redditService.getResults(token, subreddit);
		  	    
	}
}
