package com.movieaccess.rest;

import com.movieaccess.rest.controller.PostController;
import com.movieaccess.rest.service.PostService;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class JUnitControllerTest {
    @InjectMocks
    private PostController postController;

    @Mock
    private PostService postService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPostController() {
        PostController postController = new PostController(postService);
        assertNotNull(postController.getAllPosts());
    }
}
