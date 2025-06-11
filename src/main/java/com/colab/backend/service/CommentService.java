package com.colab.backend.service;

import com.colab.backend.model.Comment;
import com.colab.backend.model.Post;
import com.colab.backend.repository.CommentRepository;
import com.colab.backend.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public List<Comment> getComments(){
        return commentRepository.findAll();
    }

//    public List<Comment> getCommentsByPostId(Integer postId){
//        Optional<Post> post = postRepository.findById(postId);
//        post.map(p -> commentRepository.findByPost(p));
//        return null;
//    }
}
