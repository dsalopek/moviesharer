package com.movieaccess.rest.payload.response;

import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.model.Post;

import java.util.List;

public class PostResponse {
    private Post post;
    private Movie movie;
    private List<AttendeeResponse> attendeeResponses;

    public void setPost(Post post) {
        this.post = post;
    }

    public void setAttendeeResponses(List<AttendeeResponse> attendeeResponses) {
        this.attendeeResponses = attendeeResponses;
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

    public List<AttendeeResponse> getAttendeeResponses() {
        return attendeeResponses;
    }
}
