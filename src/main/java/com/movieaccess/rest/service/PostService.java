package com.movieaccess.rest.service;

import com.movieaccess.rest.dao.PostRepository;
import com.movieaccess.rest.model.Post;
import com.movieaccess.rest.payload.PostResponse;
import com.movieaccess.rest.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    private PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Long id){
        return postRepository.findByPostId(id);
    }
}

