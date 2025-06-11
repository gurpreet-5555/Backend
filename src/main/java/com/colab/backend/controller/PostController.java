package com.colab.backend.controller;

import com.colab.backend.model.Post;
import com.colab.backend.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/getPosts")
    public List<Post> getPosts(){
        return postService.getPosts();
    }

}
