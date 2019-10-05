package com.movieaccess.demo.comment;

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
    public List<Comment> getCommentsByMovie(@PathVariable int movieId){
        return this.commentService.getAllComments(movieId);
    }

    @GetMapping("/movies/{movieId}/comments/{commentId}")
    public Comment getCommentById(@PathVariable int commentId){
        return this.commentService.getOneComment(commentId);
    }

    @PostMapping("/movies/{movieId}/comments")
    public Comment addComment(@RequestBody Comment comment,
                           @PathVariable int movieId){
        //comment.setMovie(new Movie(movieId));
        return this.commentService.addComment(comment);
    }

    @PutMapping("/movies/{movieId}/comments/{commentId}")
    public Comment updateComment(@RequestBody Comment comment,
                              @PathVariable int commentId,
                              @PathVariable int movieId){
        return this.commentService.updateComment(comment, commentId, movieId);
    }
}
