package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CommentDaoImpl implements CommentDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<Comment> getAllCommentsByMovieId(int movieId) {
        return jdbcTemplate.query("select * from comment where movieid = ? and active = true",
                new Object[]{movieId},
                (rs, i) -> new Comment(
                        rs.getInt("id"),
                        rs.getInt("movieid"),
                        rs.getString("content"),
                        rs.getString("addedby"),
                        rs.getDate("addeddate"),
                        rs.getBoolean("active")
                ));
    }

    @Override
    public Comment getCommentById(int id) {
        return jdbcTemplate.queryForObject("select * from comment where id = ? and active = true",
                new Object[]{id},
                (rs, i) -> new Comment(
                        rs.getInt("id"),
                        rs.getInt("movieid"),
                        rs.getString("content"),
                        rs.getString("addedby"),
                        rs.getDate("addeddate"),
                        rs.getBoolean("active")
                ));
    }

    @Override
    public void createComment(Comment comment) {
        jdbcTemplate.update("insert into comment (active, addedby, addeddate, content, movieid) values (?, ?, ?, ?, ?)",
                comment.isActive(),
                comment.getAddedBy(),
                comment.getAddedDate(),
                comment.getContent(),
                comment.getMovieId()
                );
    }

    @Override
    public void updateComment(Comment comment) {
        jdbcTemplate.update("update comment set content = ?, active = ? where id = ?",
                comment.getContent(),
                comment.isActive(),
                comment.getId());
    }

    @Override
    public void deleteComment(int id) {
        jdbcTemplate.update("update comment set active = false where id = ?", id);
    }

    @Override
    public void deleteCommentsByMovieId(int movieId) {
        jdbcTemplate.update("update comment set active = false where movieid = ?", movieId);
    }
}
