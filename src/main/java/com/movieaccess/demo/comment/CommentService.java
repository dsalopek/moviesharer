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
        commentRepository.findAllByMovieId(movieId).forEach(comments::add);
        return comments;
    }

    public Comment getOneComment(int commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new CommentNotFoundException("Comment not found for commentId: " + commentId));
    }

    public Comment addComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment updateComment(Comment newComment, int commentId, int movieId) {
        return commentRepository.findById(newComment.getId())
                .map(comment -> {
                    comment.setContent(newComment.getContent());
                    comment.setMovie(new Movie(movieId));
                    comment.setAddedDate(new Date(System.currentTimeMillis()));
                    comment.setAddedBy("dylan");
                    comment.setActive(newComment.isActive());
                    return commentRepository.save(comment);
                })
                .orElseGet(() -> {
                    newComment.setId(commentId);
                    newComment.setMovie(new Movie(movieId));
                    return commentRepository.save(newComment);
                });
    }
}
