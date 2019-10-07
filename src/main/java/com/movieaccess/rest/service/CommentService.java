package com.movieaccess.rest.service;

import com.movieaccess.rest.model.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByMovieId(int movieId);
    Comment getCommentById(int id);
    void createComment(Comment comment, int movieId);
    void updateComment(Comment comment);
    void deleteComment(int id);
}
