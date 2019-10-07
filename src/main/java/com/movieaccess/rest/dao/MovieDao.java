package com.movieaccess.rest.dao;

import com.movieaccess.rest.model.Movie;

import java.util.List;

public interface MovieDao {
    List<Movie> getAllMovies();
    Movie getMovieById(int id);
    void createMovie(Movie movie);
    void updateMovie(Movie movie);
    void deleteMovie(int id);
}
