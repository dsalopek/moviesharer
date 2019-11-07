package com.movieaccess.rest.payload;

import com.movieaccess.rest.model.Attendee;
import com.movieaccess.rest.model.Location;
import com.movieaccess.rest.model.Movie;

import java.time.Instant;
import java.util.List;

public class PostResponse {
    private long postId;
    private Instant proposedDate;
    private String createdBy;
    private List<Attendee> attendeeList;
    private Movie movie;
    private Location location;


    public void setPostId(long postId) {
        this.postId = postId;
    }

    public void setProposedDate(Instant proposedDate) {
        this.proposedDate = proposedDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setAttendeeList(List<Attendee> attendeeList) {
        this.attendeeList = attendeeList;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
