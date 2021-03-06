package com.movieaccess.rest.payload.request;

import java.util.List;

public class PostRequest {
    private MovieRequest movie;
    private long proposedDate;
    private List<AttendeeRequest> attendeeList;

    public MovieRequest getMovie() {
        return movie;
    }

    public long getProposedDate() {
        return proposedDate;
    }

    public List<AttendeeRequest> getAttendeeList() {
        return attendeeList;
    }
}