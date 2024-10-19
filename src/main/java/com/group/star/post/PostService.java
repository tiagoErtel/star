package com.group.star.post;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Service
public class PostService {
	
	private PostHttpClient postHttpClient(){
		RestClient restClient = RestClient.create("https://jsonplaceholder.typicode.com/");
		HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(RestClientAdapter.create(restClient)).build();
		return factory.createClient(PostHttpClient.class);
	}
	
	public List<Post> findAll(){
		PostHttpClient client = postHttpClient();
		return client.findAll();
	}

}
