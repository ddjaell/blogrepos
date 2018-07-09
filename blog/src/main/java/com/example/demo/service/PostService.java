package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.entities.Post;
import com.example.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class PostService {
	@Autowired
	private PostRepository postRepository;
	
	public List<Post> getAllPosts(){
		return postRepository.findAll();
	}
	
	public void insert(Post post)
	{
		postRepository.save(post);
	}
}
