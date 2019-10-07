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
        return this.commentService.getAllCommentsByMovieId(movieId);
    }

    @GetMapping("/movies/{movieId}/comments/{commentId}")
    public Comment getCommentById(@PathVariable int commentId) {
        return this.commentService.getCommentById(commentId);
    }

    @PostMapping("/movies/{movieId}/comments")
    public void addComment(@RequestBody Comment comment,
                           @PathVariable int movieId) {
        this.commentService.createComment(comment, movieId);
    }

    @PutMapping("/movies/{movieId}/comments")
    public void updateComment(@RequestBody Comment comment) {
        this.commentService.updateComment(comment);
    }

    @DeleteMapping("/movies/{movieId}/comments/{commentId}")
    public void deleteComment(@PathVariable int commentId){
        this.commentService.deleteComment(commentId);
    }
}
