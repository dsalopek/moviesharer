package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    boolean existsByTmdbId(String tmdbId);

    @Query(value = "select * from movie m where m.tmdb_id = :tmdbId", nativeQuery = true)
    Optional<Movie> findMovieByTmdbId(@Param("tmdbId") String tmdb);

    @Query(value = "select * from movie m where m.movie_id = :movieId", nativeQuery = true)
    Optional<Movie> findMovieByMovieId(@Param("movieId") long movieId);

    @Query(value = "select * from movie m where m.movie_id in :movieIds", nativeQuery = true)
    Optional<List<Movie>> findAllByMovieIdIn(@Param("movieIds") List<Long> movieIds);

    @Query(value = "select * from movie m", nativeQuery = true)
    List<Movie> findAll();
}
