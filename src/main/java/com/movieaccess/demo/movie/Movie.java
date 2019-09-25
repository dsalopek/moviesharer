package com.movieaccess.demo.movie;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "movie")
public class Movie implements Comparable<Movie>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "imdbid")
    private String imdbId;

    @Column(name = "addedby")
    private String addedBy;

    @Column(name = "addeddate")
    private Date addedDate;

    @Column(name = "active")
    private boolean active;

    public Movie() {}

    public Movie(int id, String imdbId, String addedBy, Date addedDate, boolean active) {
        this.id = id;
        this.imdbId = imdbId;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
        this.active = active;
        this.addedDate = new Date("2019-04-23");
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
