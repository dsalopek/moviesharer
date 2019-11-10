package com.movieaccess.rest.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long postId;
    private long movieId;
    private long locationId;
    private Instant proposedDate;
    private Instant createdDate;
    private String createdBy;

    public Post() {
    }

    public Post(long postId, long movieId, long locationId, Instant proposedDate, Instant createdDate, String createdBy) {
        this.postId = postId;
        this.movieId = movieId;
        this.locationId = locationId;
        this.proposedDate = proposedDate;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getMovieId() {
        return movieId;
    }

    public void setMovieId(long movieId) {
        this.movieId = movieId;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public Instant getProposedDate() {
        return proposedDate;
    }

    public void setProposedDate(Instant proposedDate) {
        this.proposedDate = proposedDate;
    }

    public Instant getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Instant createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
}
