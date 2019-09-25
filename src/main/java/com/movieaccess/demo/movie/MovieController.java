package com.movieaccess.demo.movie;

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
    public List<Movie> getAllMovies(){
        return this.movieService.getAllMovies();
    }

    @GetMapping("/movies/{id}" )
    public Movie getSingleMovie(@PathVariable(value = "id") int id){
        return this.movieService.getOneMovie(id);
    }

    @PostMapping("/movies")
    public void addMovie(@RequestBody Movie movie){
        this.movieService.addMovie(movie);
    }

    @PutMapping("/movies/{id}")
    public void updateMovie(@PathVariable(value = "id") int id, @RequestBody Movie movie){
        movie.setId(id);
        this.movieService.updateMovie(movie);
    }

}
