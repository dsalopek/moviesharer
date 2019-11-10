package com.movieaccess.rest.controller;

import com.movieaccess.rest.model.Comment;
import com.movieaccess.rest.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/movies/{movieId}/comments")
    public List<Comment> getCommentsByMovie(@PathVariable int movieId) {
        return this.commentService.getAllCommentsByPostId(movieId);
    }

    @GetMapping("/movies/{movieId}/comments/{commentId}")
    public Comment getCommentById(@PathVariable int commentId) {
        return this.commentService.getCommentByCommentId(commentId);
    }

    @PostMapping("/movies/{movieId}/comments")
    public void addComment(@RequestBody Comment comment,
                           @PathVariable int movieId) {
        this.commentService.saveComment(comment);
    }
}
