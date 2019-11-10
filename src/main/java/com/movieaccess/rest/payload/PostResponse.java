package com.movieaccess.rest.payload;

import com.movieaccess.rest.model.Attendee;
import com.movieaccess.rest.model.Location;
import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.model.Post;

import java.time.Instant;
import java.util.List;

public class PostResponse {
    private Post post;
    private Movie movie;
    private List<Attendee> attendeeList;

    public void setPost(Post post) {
        this.post = post;
    }

    public void setAttendeeList(List<Attendee> attendeeList) {
        this.attendeeList = attendeeList;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Post getPost() {
        return post;
    }

    public List<Attendee> getAttendeeList() {
        return attendeeList;
    }
}
