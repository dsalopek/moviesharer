package com.movieaccess.rest.model;
import java.util.Date;

public class Comment implements Comparable<Comment> {

    private int id;
    private int movieId;
    private String content;
    private String addedBy;
    private Date addedDate;
    private boolean active;

    public Comment() {
    }

    public Comment(int id, int movieId, String content, String addedBy, Date addedDate, boolean active) {
        this.id = id;
        this.movieId = movieId;
        this.content = content;
        this.addedBy = addedBy;
        this.addedDate = addedDate;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAddedBy() {
        return addedBy;
    }

    public void setAddedBy(String addedBy) {
        this.addedBy = addedBy;
    }

    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int compareTo(Comment o) {
        return o.getAddedDate().compareTo(this.getAddedDate());
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", content='" + content + '\'' +
                ", addedBy='" + addedBy + '\'' +
                ", addedDate=" + addedDate +
                ", active=" + active +
                '}';
    }
}
