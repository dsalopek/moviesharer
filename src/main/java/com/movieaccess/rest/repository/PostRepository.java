package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByPostId(Long id);
    @Query(value = "select * from Post p", nativeQuery = true)
    List<Post> findAll();
}
