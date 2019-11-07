package com.movieaccess.rest.service;

import com.movieaccess.rest.dao.CommentRepository;
import com.movieaccess.rest.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public List<Comment> getAllCommentsByPostId(long postId){
        return this.commentRepository.findAllByPostId(postId);
    }

    public Comment getCommentByCommentId(long commentId){
        return this.commentRepository.findByCommentId(commentId);
    }

    public Comment saveComment(Comment comment){
        return this.commentRepository.save(comment);
    }
}
