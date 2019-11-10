package com.movieaccess.rest.repository;

import com.movieaccess.rest.model.Response;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResponseRepository extends JpaRepository<Response, Long> {
    public List<Response> findAll();
    public Response findByResponseId(long responseId);
}
