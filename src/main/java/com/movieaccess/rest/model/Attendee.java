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
    private long userId;
    private Instant createdDate;
    private String createdBy;

    public Attendee() {
    }

    public Attendee(long postId, long responseId, long userId, Instant createdDate, String createdBy) {
        this.postId = postId;
        this.responseId = responseId;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
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
