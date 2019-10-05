package com.movieaccess.demo.movie;

import java.util.Date;

public class Movie implements Comparable<Movie> {
    private int id;
    private String imdbId;
    private String addedBy;
    private Date addedDate;
    private boolean active;

    public Movie() {}

    public Movie(int id) {
        this.id = id;
    }

    public Movie(int id, String imdbId, String addedBy, Date addedDate, boolean active) {
        this.id = id;
        this.imdbId = imdbId;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", addedBy='" + addedBy + '\'' +
                ", addedDate=" + addedDate +
                ", active=" + active +
                '}';
    }

    @Override
    public int compareTo(Movie o) {
        return o.getAddedDate().compareTo(this.getAddedDate());
    }
}
