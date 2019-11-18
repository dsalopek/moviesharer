package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    @Query(value = "select * from attendee a where a.post_id = :postId", nativeQuery = true)
    List<Attendee> findAllByPostId(@Param("postId") Long postId);
    @Query(value = "select * from attendee a", nativeQuery = true)
    List<Attendee> findAll();
    @Query(value = "update attendee a set a.responseId = :reponseId where a.postId = :postId and a.username = :username", nativeQuery = true)
    Optional<Attendee> udpateResponse(@Param("postId") long postId, @Param("responseId") long responseId, @Param("username") String username);
}
