package com.group.star.post;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/posts")
public class PostController {
	
	@Autowired
	private PostService postService;

	@GetMapping("")
	List<Post> findAll(){
		return postService.findAll();
	}
}
