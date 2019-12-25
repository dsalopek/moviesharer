package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    @Query(value = "select * from Post p where p.post_id = :id", nativeQuery = true)
    Optional<Post> findByPostId(@Param("id") Long id);

    @Query(value = "select * from Post p", nativeQuery = true)
    List<Post> findAll();

    @Query(value = "select p.* from post p left join attendee a on p.post_id = a.post_id left join users u on u.id = a.user_id " +
            "where p.created_by = :username or u.username = :username group by p.post_id", nativeQuery = true)
    List<Post> findAllByCreatedByOrAttendee(@Param("username") String username);
}
