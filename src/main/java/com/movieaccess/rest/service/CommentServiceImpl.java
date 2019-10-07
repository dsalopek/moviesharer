package com.movieaccess.rest.service;

import com.movieaccess.rest.model.Comment;
import com.movieaccess.rest.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentDao commentDao;

    @Autowired
    public CommentServiceImpl(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    public List<Comment> getAllCommentsByMovieId(int movieId) {
        return commentDao.getAllCommentsByMovieId(movieId);
    }

    @Override
    public Comment getCommentById(int id) {
        return commentDao.getCommentById(id);
    }

    @Override
    public void createComment(Comment comment, int movieId) {
        comment.setMovieId(movieId);
        comment.setAddedBy("dylan.service");
        comment.setAddedDate(new Date(System.currentTimeMillis()));
        comment.setActive(true);

        commentDao.createComment(comment);
    }

    @Override
    public void updateComment(Comment comment) {
        comment.setActive(true);
        commentDao.updateComment(comment);
    }

    @Override
    public void deleteComment(int id) {
        commentDao.deleteComment(id);
    }


}
