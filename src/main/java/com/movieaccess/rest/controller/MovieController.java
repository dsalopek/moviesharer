package com.movieaccess.rest.controller;

import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieController {

    private MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return this.movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}")
    public Movie getMovieById(@PathVariable(value = "id") int id) {
        return this.movieService.getMovieById(id);
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody Movie movie) {
        this.movieService.createMovie(movie);
    }

    @PutMapping("/movies")
    public void updateMovie(@RequestBody Movie movie) {
        this.movieService.updateMovie(movie);
    }

    @DeleteMapping("/movies/{id}")
    public void deleteMovie(@PathVariable(value = "id") int id) {
        movieService.deleteMovie(id);
    }

}
