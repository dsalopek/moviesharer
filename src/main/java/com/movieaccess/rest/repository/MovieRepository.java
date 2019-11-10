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
    boolean existsByImdbId(String imdbId);

    @Query(value = "select * from Movie m where m.imdbId = :imdbId", nativeQuery = true)
    Optional<Movie> findMovieByImdbId(@Param("imdbId") String imdbId);

    @Query(value = "select * from Movie m where m.movieId = :imdbId", nativeQuery = true)
    Optional<Movie> findMovieByMovieId(@Param("imdbId") long movieId);

    @Query(value = "select * from Movie m", nativeQuery = true)
    List<Movie> findAll();
}
