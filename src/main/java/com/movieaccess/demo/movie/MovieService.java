package com.movieaccess.demo.movie;

import com.movieaccess.demo.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<Movie>();
        movieRepository.findAll().forEach(movies::add);
//        Collections.sort(movies);
        return movies;
    }

    public Movie getOneMovie(Integer id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new MovieNotFoundException("Movie not found for movieId: " + id));
    }

    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    public Movie updateMovie(Movie newMovie, int id) {
        return movieRepository.findById(id)
                .map(movie -> {
                    movie.setImdbId(newMovie.getImdbId());
                    movie.setAddedBy("dylan");
                    movie.setAddedDate(new Date(System.currentTimeMillis()));
                    movie.setActive(newMovie.isActive());
                    return movieRepository.save(movie);
                })
                .orElseGet(() -> {
                    newMovie.setId(id);
                    return movieRepository.save(newMovie);
                });
    }
}
