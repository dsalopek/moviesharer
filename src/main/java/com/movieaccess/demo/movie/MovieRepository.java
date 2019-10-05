package com.movieaccess.demo.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MovieRepository {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<Movie> findAll() {
        return jdbcTemplate.query("select * from movie where active = true",
                (rs, i) -> new Movie(
                        rs.getInt("id"),
                        rs.getString("imdbid"),
                        rs.getString("addedby"),
                        rs.getDate("addeddate"),
                        rs.getBoolean("active")
                ));
    }

    @Transactional(readOnly = true)
    public Movie findOne(int id) {
        return jdbcTemplate.queryForObject("select * from movie where id = ? and active = true", new Object[]{id},
                (resultSet, i) -> new Movie(
                        resultSet.getInt("id"),
                        resultSet.getString("imdbid"),
                        resultSet.getString("addedby"),
                        resultSet.getDate("addeddate"),
                        resultSet.getBoolean("active")
                ));
    }
}