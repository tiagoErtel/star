package com.group.star.post;

import java.util.List;

import org.springframework.web.service.annotation.GetExchange;

public interface PostHttpClient {
	
	@GetExchange("/posts")
	public List<Post> findAll();

}
