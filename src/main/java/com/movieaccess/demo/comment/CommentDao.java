package com.movieaccess.demo.comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getAllCommentsByMovieId(int id);
    Comment getCommentById(int id);
    void createComment(Comment comment);
    void updateComment(Comment comment);
    void deleteComment(int id);
    void deleteCommentsByMovieId(int movieId);
}
