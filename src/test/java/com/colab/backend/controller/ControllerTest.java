package com.colab.backend.controller;

import com.colab.backend.model.Comment;
import com.colab.backend.model.Post;
import com.colab.backend.model.User;
import com.colab.backend.service.PostService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ControllerTest {

    @Mock
    private PostService postService;

    private Controller controller;

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

    @BeforeEach
    void setUp(){
        controller = new Controller(postService);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    }

    @Test
    void testGetPosts() throws JSONException {
        User testUser = new User();
        testUser.setUsername("John Doe");

        Post testPost = new Post();
        testPost.setTitle("Title");
        testPost.setContent("Content");
        testPost.setAuthor(testUser);
        testPost.setId(1);
        testPost.setCreated_at(new Date(20251106));

        Mockito.when(postService.getPosts()).thenReturn(List.of(testPost));

        ResponseEntity<String> response = controller.getPosts();

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JSONArray jsonArray = new JSONArray(response.getBody());
        JSONObject post = jsonArray.getJSONObject(0);

        assertEquals(post.get("id"), 1);
        assertEquals(post.get("title"), "Title");
        assertEquals(post.get("content"), "Content");
        assertEquals(post.get("author"), "John Doe");

    }

    @Test
    void testCommentsByPost() throws JSONException {
        User testUser = new User();
        testUser.setUsername("John Doe");

        Comment comment1 = new Comment();
        comment1.setAuthor(testUser);
        comment1.setCreated_at(new Date(20251106));
        comment1.setContent("Comment 1");
        comment1.setId(1);

        Comment comment2 = new Comment();
        comment2.setAuthor(testUser);
        comment2.setCreated_at(new Date(20251106));
        comment2.setContent("Comment 2");
        comment2.setId(2);

        Post testPost = new Post();
        testPost.setTitle("Title");
        testPost.setContent("Content");
        testPost.setAuthor(testUser);
        testPost.setId(1);
        testPost.setCreated_at(new Date(20251106));
        testPost.setComments(List.of(comment1, comment2));

        Mockito.when(postService.getPostById(1)).thenReturn(Optional.of(testPost));

        ResponseEntity<String> response = controller.getCommentsByPostIdAndPageParams(1, 2,1);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        JSONArray jsonArray = new JSONArray(response.getBody());

        JSONObject responseComment1 = jsonArray.getJSONObject(0);
        assertEquals(responseComment1.get("id"), 1);
        assertEquals(responseComment1.get("content"), "Comment 1");
        assertEquals(responseComment1.get("author"), "John Doe");

        JSONObject responseComment2 = jsonArray.getJSONObject(1);
        assertEquals(responseComment2.get("id"), 2);
        assertEquals(responseComment2.get("content"), "Comment 2");
        assertEquals(responseComment2.get("author"), "John Doe");
    }

}