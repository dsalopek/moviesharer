package com.movieaccess.demo.comment;

import com.movieaccess.demo.exception.CommentNotFoundException;
import com.movieaccess.demo.movie.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(int movieId) {
        List<Comment> comments = new LinkedList<Comment>();
        return commentRepository.commentByMovie(movieId);
    }

    public Comment getOneComment(int commentId) {
        return null;
    }

    public Comment addComment(Comment comment) {
        return null;
    }

    public Comment updateComment(Comment newComment, int commentId, int movieId) {
        return null;
    }
}
