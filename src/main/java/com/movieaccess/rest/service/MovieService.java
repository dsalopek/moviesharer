package com.movieaccess.rest.service;

import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Optional<Movie> getMovieByMovieId(long movieId){
        return this.movieRepository.findMovieByMovieId(movieId);
    }

    public Optional<Movie> getMovieByImdbId(String imdbId){
        return this.movieRepository.findMovieByImdbId(imdbId);
    }
}
