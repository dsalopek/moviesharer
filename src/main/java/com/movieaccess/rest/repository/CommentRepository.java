package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAll();
    public Comment findByCommentId(long commentId);
    public List<Comment> findAllByPostId(long postId);
    public  List<Comment> findAllByCreatedBy(String createdBy);

}
