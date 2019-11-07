package com.movieaccess.rest.service;

import com.movieaccess.rest.dao.AttendeeRepository;
import com.movieaccess.rest.model.Attendee;
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

    public List<Attendee> getAllAttendeesByPostId(long postId){
        return attendeeRepository.findAllByPostId(postId);
    }
}
