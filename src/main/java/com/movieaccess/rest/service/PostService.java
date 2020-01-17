package com.movieaccess.rest.service;

import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.model.User;
import com.movieaccess.rest.payload.request.AttendeeResponseRequest;
import com.movieaccess.rest.payload.request.MovieRequest;
import com.movieaccess.rest.payload.response.AttendeeResponse;
import com.movieaccess.rest.repository.AttendeeRepository;
import com.movieaccess.rest.repository.MovieRepository;
import com.movieaccess.rest.repository.PostRepository;
import com.movieaccess.rest.model.AttendeeReply;
import com.movieaccess.rest.model.Post;
import com.movieaccess.rest.payload.request.PostRequest;
import com.movieaccess.rest.payload.response.PostResponse;
import com.movieaccess.rest.repository.UserRepository;
import com.movieaccess.rest.security.UserPrincipal;
import com.movieaccess.rest.util.ResponseIds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostService {
    private PostRepository postRepository;
    private AttendeeRepository attendeeRepository;
    private MovieRepository movieRepository;
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, AttendeeRepository attendeeRepository, MovieRepository movieRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.attendeeRepository = attendeeRepository;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
    }

    public List<PostResponse> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        List<Movie> movies = this.movieRepository.findAll();
        List<AttendeeReply> attendeeList = this.attendeeRepository.findAll();

        return buildMultiplePostResponse(posts, movies, attendeeList);
    }

    public List<PostResponse> getPostsForUser(String username) {
        List<Post> posts = this.postRepository.findAllByCreatedBy(username);

        return getPostResponseGivenPosts(posts);
    }

    public List<PostResponse> getPostFeedForUser(String username) {
        List<Post> posts = this.postRepository.findAllByCreatedByOrAttendee(username);

        return getPostResponseGivenPosts(posts);
    }

    private List<PostResponse> getPostResponseGivenPosts(List<Post> posts) {
        List<Long> postIds = posts.stream().map(Post::getPostId).collect(Collectors.toList());
        List<Long> movieIds = posts.stream().map(Post::getMovieId).collect(Collectors.toList());
        List<Movie> movies = this.movieRepository.findAllByMovieIdIn(movieIds)
                .orElseThrow(() -> new NoSuchElementException("Movies not found with post ids: " + movieIds + "!"));
        List<AttendeeReply> attendeeList = this.attendeeRepository.findAllByPostIdIn(postIds);

        return buildMultiplePostResponse(posts, movies, attendeeList);
    }

    private List<PostResponse> buildMultiplePostResponse(List<Post> posts, List<Movie> movies, List<AttendeeReply> attendeeList) {
        Map<Long, User> userMap = getUserMap(attendeeList);
        Map<Long, List<AttendeeResponse>> attendeeResponseMap = new HashMap<>();
        for (AttendeeReply a : attendeeList) {
            User user = userMap.get(a.getUserId());
            AttendeeResponse ar = new AttendeeResponse(
                    a.getAttendeeId(),
                    a.getResponseId(),
                    user.getId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getAvatarPath());

            if (attendeeResponseMap.containsKey(a.getPostId())) {
                attendeeResponseMap.get(a.getPostId()).add(ar);
            } else {
                List<AttendeeResponse> tempAttendees = new ArrayList<>();
                tempAttendees.add(ar);
                attendeeResponseMap.put(a.getPostId(), tempAttendees);
            }
        }

        Map<Long, Movie> movieMap = new HashMap<>();
        for (Movie m : movies) {
            movieMap.put(m.getMovieId(), m);
        }

        return posts.stream().map(p -> {
            PostResponse ps = new PostResponse();
            ps.setPost(p);
            ps.setAttendeeResponses(attendeeResponseMap.get(p.getPostId()));
            ps.setMovie(movieMap.get(p.getMovieId()));

            return ps;
        }).collect(Collectors.toList());
    }

    private Map<Long, User> getUserMap(List<AttendeeReply> attendeeList) {
        List<Long> userIds = attendeeList.stream().map(AttendeeReply::getUserId).collect(Collectors.toList());
        List<User> users = this.userRepository.findByIdIn(userIds);
        Map<Long, User> userMap = new HashMap<>();
        for (User user : users) {
            if (!userMap.containsKey(user.getId())) {
                userMap.put(user.getId(), user);
            }
        }
        return userMap;
    }

    public PostResponse getPostById(Long id) {
        Post post = this.postRepository.findByPostId(id)
                .orElseThrow(() ->
                        new NoSuchElementException("Post with id: " + id + " does not exist!")
                );

        Movie movie = this.movieRepository.findMovieByMovieId(post.getMovieId())
                .orElseThrow(() ->
                        new NoSuchElementException("Movie with id: " + post.getMovieId() + " does not exist!")
                );

        List<AttendeeReply> attendeeList = this.attendeeRepository.findAllByPostId(post.getPostId());

        return buildSinglePostResponse(post, movie, attendeeList);
    }

    private PostResponse buildSinglePostResponse(Post post, Movie movie, List<AttendeeReply> attendeeList) {
        Map<Long, User> userMap = getUserMap(attendeeList);
        List<AttendeeResponse> attendeeResponses = attendeeList.stream()
                .map(attendeeReply -> {
                            User user = userMap.get(attendeeReply.getUserId());
                            return new AttendeeResponse(attendeeReply.getAttendeeId(), attendeeReply.getResponseId(), user.getId(), user.getFirstName(), user.getLastName(), user.getAvatarPath());
                        }
                ).collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setPost(post);
        postResponse.setMovie(movie);
        postResponse.setAttendeeResponses(attendeeResponses);
        return postResponse;
    }

    public PostResponse createPost(UserPrincipal userPrincipal, PostRequest postRequest) {
        MovieRequest movieRequest = postRequest.getMovie();
        Movie movie = this.movieRepository.findMovieByTmdbId(movieRequest.getTmdbId())
                .orElseGet(() -> {
                    Movie tempMovie = new Movie();

                    tempMovie.setTmdbId(movieRequest.getTmdbId());
                    tempMovie.setTitle(movieRequest.getTitle());
                    tempMovie.setOverview(movieRequest.getOverview());
                    tempMovie.setPosterURL(movieRequest.getPosterURL());
                    tempMovie.setBackdropURL(movieRequest.getBackdropURL());

                    return this.movieRepository.save(tempMovie);
                });

        Post post = new Post();
        post.setProposedDate(Instant.ofEpochSecond(postRequest.getProposedDate()));
        post.setCreatedBy(userPrincipal.getUsername());
        post.setCreatedDate(Instant.now());
        post.setMovieId(movie.getMovieId());

        post = this.postRepository.save(post);

        Post finalPost = post;
        List<AttendeeReply> attendeeList = postRequest.getAttendeeList().stream().map(attendeeRequest -> {
            AttendeeReply attendee = new AttendeeReply();
            attendee.setPostId(finalPost.getPostId());
            attendee.setCreatedDate(Instant.now());
            attendee.setUserId(attendeeRequest.getId());
            attendee.setCreatedBy(userPrincipal.getUsername());
            attendee.setResponseId(ResponseIds.NORESPONSE);
            return attendee;
        }).collect(Collectors.toList());

        attendeeList = this.attendeeRepository.saveAll(attendeeList);

        logger.info("Attendee(s) created for post id: " + post.getPostId());
        logger.info(attendeeList.toString());

        return buildSinglePostResponse(post, movie, attendeeList);
    }

    public AttendeeReply respondToInvite(long postId, long attendeeId, AttendeeResponseRequest attendeeResponse) {
        AttendeeReply attendee = this.attendeeRepository.getOne(attendeeId);
        attendee.setResponseId(attendeeResponse.getResponseId());
        return this.attendeeRepository.save(attendee);
    }
}

