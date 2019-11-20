package com.movieaccess.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long movieId;
    private String tmdbId;
    private String title;
    private String overview;
    private String posterURL;
    private String backdropURL;

    public Movie() {
    }

    public Movie(String tmdbId, String title, String overview, String posterURL, String backdropURL) {
        this.tmdbId = tmdbId;
        this.title = title;
        this.overview = overview;
        this.posterURL = posterURL;
        this.backdropURL = backdropURL;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public String getTmdbId() {
        return tmdbId;
    }

    public void setTmdbId(String tmdbId) {
        this.tmdbId = tmdbId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    public String getBackdropURL() {
        return backdropURL;
    }

    public void setBackdropURL(String backdropURL) {
        this.backdropURL = backdropURL;
    }
}
