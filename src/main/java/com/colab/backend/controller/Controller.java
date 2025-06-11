package com.colab.backend.controller;

import com.colab.backend.model.Comment;
import com.colab.backend.model.Post;
import com.colab.backend.service.CommentService;
import com.colab.backend.service.PostService;
import com.colab.backend.service.UserService;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api")
public class Controller {

    @Autowired
    private UserService userService; //Unused

    @Autowired
    private PostService postService;

    @Autowired
    private CommentService commentService; //Unused

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");

    @GetMapping("/posts")
    public ResponseEntity<String> getPosts(){
        List<Post> allPosts = postService.getPosts();
        JSONArray response = new JSONArray();
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        allPosts.stream().forEach(post -> {
            response.put(new JSONObject()
                    .put("id", post.getId())
                    .put("title", post.getTitle())
                    .put("content", post.getContent())
                    .put("author", post.getAuthor().getUsername())
                    .put("createdAt", simpleDateFormat.format(post.getCreated_at()))
                    .put("commentCount", post.getComments().stream().count())
                    .put("userPreview", new ArrayList<>()));
        });
        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<String> getCommentsByPostIdAndPageParams(@PathVariable("postId") Integer postId,
                                                                   @RequestParam("per-page") Integer perPage,
                                                                   @RequestParam("page") Integer page)
    {
        JSONArray response = new JSONArray();
        Optional<Post> postById = postService.getPostById(postId);
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        postById.ifPresent(p -> {
            p.getComments().stream().skip((page-1)*perPage).limit(perPage).forEach(comment -> {
                response.put(new JSONObject()
                        .put("id", comment.getId())
                        .put("content", comment.getContent())
                        .put("author", comment.getAuthor().getUsername())
                        .put("createdAt", simpleDateFormat.format(comment.getCreated_at())));
            });
        });
        return new ResponseEntity<String>(response.toString(), HttpStatus.OK);
    }

}
