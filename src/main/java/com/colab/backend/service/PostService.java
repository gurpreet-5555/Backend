package com.colab.backend.service;

import com.colab.backend.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PostService {
    List<Post> getPosts();
    Optional<Post> getPostById(Integer postId);
}
