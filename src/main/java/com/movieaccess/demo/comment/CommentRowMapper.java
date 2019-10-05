package com.movieaccess.demo.comment;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CommentRowMapper implements RowMapper<Comment> {
    @Override
    public Comment mapRow(ResultSet resultSet, int i) throws SQLException {
        Comment comment = new Comment();

        resultSet.getInt("id");
        resultSet.getInt("movieid");
        resultSet.getString("content");
        resultSet.getString("addedby");
        resultSet.getDate("addeddate");
        resultSet.getBoolean("active");

        return comment;
    }
}
