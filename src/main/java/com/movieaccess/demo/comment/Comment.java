package com.movieaccess.demo.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.movieaccess.demo.movie.Movie;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Comment implements Comparable<Comment>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;

    @Column(name = "content")
    private String content;

    @Column(name="addedby")
    private String addedBy;

    @Column(name="addeddate")
    private Date addedDate;

    @Column(name="active")
    private boolean active;

    public Comment() {}

    public Comment(int id, Movie movie, String content, String addedBy, Date addedDate, boolean active) {
        this.id = id;
        this.movie = movie;
        this.content = content;
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

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
    public int compareTo(Comment o) {
        return o.getAddedDate().compareTo(this.getAddedDate());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", addedBy='" + addedBy + '\'' +
                ", addedDate=" + addedDate +
                ", active=" + active +
                '}';
    }
}
