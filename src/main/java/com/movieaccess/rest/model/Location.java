package com.movieaccess.rest.model;

import javax.persistence.*;

@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long locationId;
    private String displayValue;
    private String description;

    public Location() {
    }

    public Location(long locationId, String displayValue, String description) {
        this.locationId = locationId;
        this.displayValue = displayValue;
        this.description = description;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
