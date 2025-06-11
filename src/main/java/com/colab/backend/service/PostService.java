package com.colab.backend.service;

import com.colab.backend.model.Post;
import com.colab.backend.repository.PostRepository;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Optional<Post> getPostById(Integer postId){
        return postRepository.findById(postId);
    }
}
