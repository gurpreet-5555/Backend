package com.colab.backend.service;

import com.colab.backend.model.Comment;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    List<Comment> getComments();
}
