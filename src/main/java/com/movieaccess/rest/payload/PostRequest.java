package com.movieaccess.rest.payload;

import java.time.Instant;
import java.util.List;

public class PostRequest {
    private MovieRequest movie;
    private Instant proposedDate;
    private List<AttendeeRequest> attendeeList;

    public MovieRequest getMovie() {
        return movie;
    }

    public Instant getProposedDate() {
        return proposedDate;
    }

    public List<AttendeeRequest> getAttendeeList() {
        return attendeeList;
    }
}