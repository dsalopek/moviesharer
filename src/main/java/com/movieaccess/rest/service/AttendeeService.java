package com.movieaccess.rest.service;

import com.movieaccess.rest.repository.AttendeeRepository;
import com.movieaccess.rest.model.AttendeeReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendeeService {
    private AttendeeRepository attendeeRepository;

    @Autowired
    public AttendeeService(AttendeeRepository attendeeRepository) {
        this.attendeeRepository = attendeeRepository;
    }

    public List<AttendeeReply> getAllAttendeesByPostId(long postId){
        return attendeeRepository.findAllByPostId(postId);
    }
}
