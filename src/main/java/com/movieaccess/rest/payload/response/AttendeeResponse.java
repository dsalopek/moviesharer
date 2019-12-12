package com.movieaccess.rest.payload.response;

public class AttendeeResponse {
    private long attendeeId;
    private long responseId;
    private long userId;
    private String firstName;
    private String lastName;
    private String avatarPath;

    public AttendeeResponse(long attendeeId, long responseId, long userId, String firstName, String lastName, String avatarPath) {
        this.attendeeId = attendeeId;
        this.responseId = responseId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.avatarPath = avatarPath;
    }

    public long getAttendeeId() {
        return attendeeId;
    }

    public void setAttendeeId(long attendeeId) {
        this.attendeeId = attendeeId;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatarPath() {
        return avatarPath;
    }

    public void setAvatarPath(String avatarPath) {
        this.avatarPath = avatarPath;
    }
}
