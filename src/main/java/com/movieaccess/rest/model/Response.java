package com.movieaccess.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "response")
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long responseId;
    private String displayValue;
    private String test;

    public Response() {
    }

    public Response(long responseId, String displayValue) {
        this.responseId = responseId;
        this.displayValue = displayValue;
    }

    public long getResponseId() {
        return responseId;
    }

    public void setResponseId(long responseId) {
        this.responseId = responseId;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }
}