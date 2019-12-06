//package com.movieaccess.rest.util;
//
//import com.movieaccess.rest.model.Attendee;
//import com.movieaccess.rest.model.Location;
//import com.movieaccess.rest.model.Movie;
//import com.movieaccess.rest.model.Post;
//import com.movieaccess.rest.payload.response.PostResponse;
//
//import java.util.List;
//
//public class PostMapper {
//    public static PostResponse mapToPostResponse(Post post, Movie movie, List<Attendee> attendeeList, Location location){
//        PostResponse postResponse = new PostResponse();
//
//        postResponse.setPostId(post.getPostId());
//        postResponse.setProposedDate(post.getProposedDate());
//        postResponse.setCreatedBy(post.getCreatedBy());
//        postResponse.setMovie(movie);
//        postResponse.setAttendeeList(attendeeList);
//        postResponse.setLocation(location);
//
//        return postResponse;
//    }
//}
