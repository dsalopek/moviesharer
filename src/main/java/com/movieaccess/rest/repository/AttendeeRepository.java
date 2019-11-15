package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Attendee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendeeRepository extends JpaRepository<Attendee, Long> {
    @Query(value = "select * from Attendee a where a.post_id = :postId", nativeQuery = true)
    List<Attendee> findAllByPostId(@Param("postId") Long postId);
    @Query(value = "select * from Attendee a", nativeQuery = true)
    List<Attendee> findAll();
}
