package com.colab.backend.service;

import com.colab.backend.model.Comment;
import com.colab.backend.repository.CommentRepository;
import com.colab.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

}
