package com.movieaccess.rest.model;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "attendee")
public class Attendee {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long attendeeId;
    private long postId;
    private long responseId;
    private String username;
    private Instant createdDate;
    private String createdBy;

    public Attendee() {
    }

    public Attendee(long attendeeId, long postId, long responseId, String username, Instant createdDate, String createdBy) {
        this.attendeeId = attendeeId;
        this.postId = postId;
        this.responseId = responseId;
        this.username = username;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(long attendeeId) {
        this.attendeeId = attendeeId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public long getResponseId() {
        return responseId;
    }

    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
