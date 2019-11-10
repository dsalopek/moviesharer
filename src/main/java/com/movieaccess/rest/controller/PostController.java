package com.movieaccess.rest.controller;

import com.movieaccess.rest.model.Post;
import com.movieaccess.rest.payload.PostRequest;
import com.movieaccess.rest.payload.PostResponse;
import com.movieaccess.rest.security.CurrentUser;
import com.movieaccess.rest.security.UserPrincipal;
import com.movieaccess.rest.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public List<PostResponse> getAllPosts(){
        return postService.getAllPosts();
    }

    @PostMapping("/")
    public PostResponse createPost(@CurrentUser UserPrincipal userPrincipal, @RequestBody PostRequest postRequest) {
        return this.postService.createPost(userPrincipal, postRequest);
    }
}
