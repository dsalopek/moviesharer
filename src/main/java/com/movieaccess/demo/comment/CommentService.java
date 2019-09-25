package com.movieaccess.demo.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllComments(int movieId){
        List<Comment> comments = new LinkedList<Comment>();
        commentRepository.findAllByMovieId(movieId).forEach(comments::add);
        return comments;
    }

    public Comment getOneComment(int commentId){
        return commentRepository.findById(commentId).get();
    }

    public void addComment(Comment comment){
        commentRepository.save(comment);
    }

    public void updateComment(Comment comment){
        commentRepository.save(comment);
    }
}
