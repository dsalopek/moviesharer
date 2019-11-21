package com.movieaccess.rest.service;

import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.payload.AttendeeResponseRequest;
import com.movieaccess.rest.payload.MovieRequest;
import com.movieaccess.rest.repository.AttendeeRepository;
import com.movieaccess.rest.repository.MovieRepository;
import com.movieaccess.rest.repository.PostRepository;
import com.movieaccess.rest.model.Attendee;
import com.movieaccess.rest.model.Post;
import com.movieaccess.rest.payload.PostRequest;
import com.movieaccess.rest.payload.PostResponse;
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

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);

    @Autowired
    public PostService(PostRepository postRepository, AttendeeRepository attendeeRepository, MovieRepository movieRepository) {
        this.postRepository = postRepository;
        this.attendeeRepository = attendeeRepository;
        this.movieRepository = movieRepository;
    }

    public List<PostResponse> getAllPosts() {
        List<Post> posts = this.postRepository.findAll();
        List<Movie> movies = this.movieRepository.findAll();
        List<Attendee> attendeeList = this.attendeeRepository.findAll();

        return buildMultiplePostResponse(posts, movies, attendeeList);
    }

    public List<PostResponse> getPostFeedForUser(String username) {
        List<Post> posts = this.postRepository.findAllByCreatedByOrAttendee(username);
        List<Long> postIds = posts.stream().map(Post::getPostId).collect(Collectors.toList());
        List<Long> movieIds = posts.stream().map(Post::getMovieId).collect(Collectors.toList());
        List<Movie> movies = this.movieRepository.findAllByMovieIdIn(movieIds)
                .orElseThrow(()->new NoSuchElementException("Movies now found with post ids: " + movieIds + "!"));
        List<Attendee> attendeeList = this.attendeeRepository.findAllByPostIdIn(postIds);

        return buildMultiplePostResponse(posts, movies, attendeeList);
    }

    private List<PostResponse> buildMultiplePostResponse(List<Post> posts, List<Movie> movies, List<Attendee> attendeeList) {
        Map<Long, List<Attendee>> attendeeMap = new HashMap<>();
        for (Attendee a : attendeeList) {
            if (attendeeMap.containsKey(a.getPostId())) {
                attendeeMap.get(a.getPostId()).add(a);
            } else {
                List<Attendee> tempAttendees = new ArrayList<>();
                tempAttendees.add(a);
                attendeeMap.put(a.getPostId(), tempAttendees);
            }
        }

        Map<Long, Movie> movieMap = new HashMap<>();
        for (Movie m : movies) {
            movieMap.put(m.getMovieId(), m);
        }

        return posts.stream().map(p -> {
            PostResponse ps = new PostResponse();
            ps.setPost(p);
            ps.setAttendeeList(attendeeMap.get(p.getPostId()));
            ps.setMovie(movieMap.get(p.getMovieId()));

            return ps;
        }).collect(Collectors.toList());
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

        List<Attendee> attendeeList = this.attendeeRepository.findAllByPostId(post.getPostId());

        return buildSinglePostResponse(post, movie, attendeeList);
    }

    private PostResponse buildSinglePostResponse(Post post, Movie movie, List<Attendee> attendeeList) {
        PostResponse postResponse = new PostResponse();
        postResponse.setPost(post);
        postResponse.setMovie(movie);
        postResponse.setAttendeeList(attendeeList);
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
        List<Attendee> attendeeList = postRequest.getAttendeeList().stream().map(attendeeRequest -> {
            Attendee attendee = new Attendee();
            attendee.setPostId(finalPost.getPostId());
            attendee.setCreatedDate(Instant.now());
            attendee.setAttendeeId(attendeeRequest.getUserId());
            attendee.setCreatedBy(userPrincipal.getUsername());
            attendee.setResponseId(ResponseIds.NORESPONSE);
            return attendee;
        }).collect(Collectors.toList());

        attendeeList = this.attendeeRepository.saveAll(attendeeList);

        return buildSinglePostResponse(post, movie, attendeeList);
    }

    public Attendee respondToInvite(long postId, long attendeeId, AttendeeResponseRequest attendeeResponse) {
        Attendee attendee = this.attendeeRepository.getOne(attendeeId);
        attendee.setResponseId(attendeeResponse.getResponseId());
        return this.attendeeRepository.save(attendee);
    }
}

