package com.movieaccess.rest.service;

import com.movieaccess.rest.model.Movie;
import com.movieaccess.rest.payload.MovieRequest;
import com.movieaccess.rest.repository.AttendeeRepository;
import com.movieaccess.rest.repository.MovieRepository;
import com.movieaccess.rest.repository.PostRepository;
import com.movieaccess.rest.model.Attendee;
import com.movieaccess.rest.model.Post;
import com.movieaccess.rest.payload.PostRequest;
import com.movieaccess.rest.payload.PostResponse;
import com.movieaccess.rest.security.JwtAuthenticationFilter;
import com.movieaccess.rest.security.UserPrincipal;
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
        logger.info("--- begin queries ---");
        List<Post> posts = this.postRepository.findAll();
        List<Movie> movies = this.movieRepository.findAll();
        List<Attendee> attendeeList = this.attendeeRepository.findAll();

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

        logger.info("--- end queries ---");

        return posts.stream().map(p -> {
            PostResponse ps = new PostResponse();
            ps.setPost(p);
            ps.setAttendeeList(attendeeMap.get(p.getPostId()));
            ps.setMovie(movieMap.get(p.getMovieId()));

            return ps;
        }).collect(Collectors.toList());
    }

    public Optional<Post> getPostById(Long id) {
        return postRepository.findByPostId(id);
    }

    public PostResponse createPost(UserPrincipal userPrincipal, PostRequest postRequest) {
        MovieRequest movieRequest = postRequest.getMovie();
        Movie movie = this.movieRepository.findMovieByImdbId(movieRequest.getImdbId())
                .orElseGet(() -> {
                    Movie tempMovie = new Movie();
                    tempMovie.setImdbId(movieRequest.getImdbId());
                    tempMovie.setTitle(movieRequest.getTitle());
                    tempMovie.setOverview(movieRequest.getOverview());

                    return this.movieRepository.save(tempMovie);
                });

        Post post = new Post();
        post.setProposedDate(postRequest.getProposedDate());
        post.setCreatedBy(userPrincipal.getUsername());
        post.setCreatedDate(Instant.now());
        post.setMovieId(movie.getMovieId());

        post = this.postRepository.save(post);

        Post finalPost = post;
        List<Attendee> attendeeList = postRequest.getAttendeeList().stream().map(attendeeRequest -> {
            Attendee attendee = new Attendee();
            attendee.setPostId(finalPost.getPostId());
            attendee.setCreatedDate(Instant.now());
            attendee.setUsername(attendeeRequest.getUsername());
            attendee.setCreatedBy(userPrincipal.getUsername());
            return attendee;
        }).collect(Collectors.toList());

        attendeeList = this.attendeeRepository.saveAll(attendeeList);

        PostResponse postResponse = new PostResponse();
        postResponse.setPost(post);
        postResponse.setAttendeeList(attendeeList);
        postResponse.setMovie(movie);

        return postResponse;
    }
}

