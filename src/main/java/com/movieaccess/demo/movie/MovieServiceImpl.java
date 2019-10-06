package com.movieaccess.demo.movie;

import com.movieaccess.demo.comment.CommentDao;
import com.movieaccess.demo.exception.MovieNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    private MovieDao movieDao;
    private CommentDao commentDao;

    @Autowired
    public MovieServiceImpl(MovieDao movieDao, CommentDao commentDao) {
        this.movieDao = movieDao;
        this.commentDao = commentDao;
    }

    public List<Movie> getAllMovies() {
        List<Movie> movies = new LinkedList<Movie>();
        movieDao.getAllMovies().forEach(movies::add);
        return movies;
    }

    public Movie getMovieById(int id) {
        Movie movie;
        movie = movieDao.getMovieById(id);

        if(movie == null){
            throw new MovieNotFoundException("Movie not found with id: "+id);
        }

        return movie;
    }

    public void createMovie(Movie movie) {
        movie.setAddedBy("dylan.service");
        movie.setAddedDate(new Date(System.currentTimeMillis()));
        movie.setActive(true);
        movieDao.createMovie(movie);
    }

    public void updateMovie(Movie movie) {
        movieDao.updateMovie(movie);
    }

    public void deleteMovie(int id) {
        movieDao.deleteMovie(id);
        commentDao.deleteCommentsByMovieId(id);
    }
}
