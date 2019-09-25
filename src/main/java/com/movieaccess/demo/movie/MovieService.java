package com.movieaccess.demo.movie;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> getAllMovies(){
        List<Movie> movies = new LinkedList<Movie>();
        movieRepository.findAll().forEach(movies::add);
        Collections.sort(movies);
        return movies;
    }

    public Movie getOneMovie(Integer id){
        return movieRepository.findById(id).get();
    }

    public void addMovie(Movie movie){
        movieRepository.save(movie);
    }

    public void updateMovie(Movie movie){
        movieRepository.save(movie);
    }
}
