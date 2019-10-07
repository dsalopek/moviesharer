package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class MovieDaoImpl implements MovieDao {
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public MovieDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional(readOnly = true)
    public List<Movie> getAllMovies() {
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
    public Movie getMovieById(int id) {
        return jdbcTemplate.queryForObject("select * from movie where id = ? and active = true",
                new Object[]{id},
                (rs, i) -> new Movie(
                        rs.getInt("id"),
                        rs.getString("imdbid"),
                        rs.getString("addedby"),
                        rs.getDate("addeddate"),
                        rs.getBoolean("active")
                ));
    }

    @Transactional
    public void createMovie(Movie movie) {
        jdbcTemplate.update("insert into movie (imdbid, addedby, addeddate, active) values (?, ?, ?, ?)",
                movie.getImdbId(),
                movie.getAddedBy(),
                movie.getAddedDate(),
                movie.isActive()
        );
    }

    @Transactional
    public void updateMovie(Movie movie) {
        jdbcTemplate.update("update movie set imdbid = ?, active = ? where id = ?",
                movie.getImdbId(),
                movie.isActive(),
                movie.getId());
    }

    @Transactional
    public void deleteMovie(int id) {
        jdbcTemplate.update("update movie set active = false where id = ?", id);
    }
}