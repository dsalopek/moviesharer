package com.movieaccess.demo.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
    public Movie getSingleMovie(@PathVariable(value = "id") int id) {
        return this.movieService.getOneMovie(id);
    }

    @PostMapping("/movies")
    public Movie addMovie(@RequestBody Movie movie) {
        return this.movieService.addMovie(movie);
    }

    @PutMapping("/movies/{id}")
    public Movie updateMovie(@PathVariable(value = "id") int id, @RequestBody Movie movie) {
        return this.movieService.updateMovie(movie, id);
    }

}
