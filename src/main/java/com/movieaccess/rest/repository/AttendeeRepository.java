package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.AttendeeReply;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AttendeeRepository extends JpaRepository<AttendeeReply, Long> {
    @Query(value = "select * from Attendee a where a.post_id = :postId", nativeQuery = true)
    List<AttendeeReply> findAllByPostId(@Param("postId") Long postId);

    @Query(value = "select * from Attendee a", nativeQuery = true)
    List<AttendeeReply> findAll();

    @Query(value = "select * from Attendee a where a.post_id in :postIds", nativeQuery = true)
    List<AttendeeReply> findAllByPostIdIn(@Param("postIds") List<Long> postIds);

    @Modifying
    @Query(value = "UPDATE Attendee a SET a.response_id = :responseId WHERE a.post_id = :postId AND a.attendee_id = :attendeeId", nativeQuery = true)
    Optional<AttendeeReply> updateResponse(@Param("postId") long postId, @Param("attendeeId") long attendeeId, @Param("responseId") long responseId);
}
