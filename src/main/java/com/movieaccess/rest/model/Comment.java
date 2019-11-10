package com.movieaccess.rest.model;
import javax.persistence.*;
import java.time.Instant;
import java.util.Date;

@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long commentId;
    private long postId;
    private String content;
    private Instant createdDate;
    private String createdBy;

    public Comment(long commentId, long postId, String content, Instant createdDate, String createdBy) {
        this.commentId = commentId;
        this.postId = postId;
        this.content = content;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public long getCommentId() {
        return commentId;
    }

    public void setCommentId(long commentId) {
        this.commentId = commentId;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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
