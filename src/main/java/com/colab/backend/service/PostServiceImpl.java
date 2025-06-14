package com.colab.backend.service;

import com.colab.backend.exception.ResourceNotFoundException;
import com.colab.backend.model.Post;
import com.colab.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    @Override
    public Optional<Post> getPostById(Integer postId){
        return Optional.of(postRepository.findById(postId)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id: "+ postId + " doesn't exist!")));
    }
}
