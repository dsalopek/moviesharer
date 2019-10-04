package com.movieaccess.demo.mapper;

import com.movieaccess.demo.movie.Movie;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class MovieMapper implements RowMapper<Movie> {
    @Override
    public Movie map(ResultSet rs, StatementContext ctx) throws SQLException {
        Movie movie = Movie.builder()
                .id(rs.getInt("id"))
                .imdbId(rs.getString("imdbid"))
                .addedBy(rs.getString("addedby"))
                .active(rs.getBoolean("active"))
                .build();

        return movie;
    }
}
