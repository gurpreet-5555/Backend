package com.colab.backend.controller;

import com.colab.backend.model.Comment;
import com.colab.backend.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/getComments")
    public List<Comment> getComments(){
        return commentService.getComments();
    }
}
