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
        Movie movie;
        try{
            movie = movieRepository.findOne(id);
        } catch(Exception e){
            //Still isnt working...other methods?
            throw new MovieNotFoundException("Movie not found with id: "+id);
        }
        return movie;
    }

    public Movie addMovie(Movie movie) {
        return null;
    }

    public Movie updateMovie(Movie newMovie, int id) {
        return null;
    }
}
