package com.colab.backend.repository;

import com.colab.backend.model.Comment;
import com.colab.backend.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {
//    List<Comment> findByPost(Post post);
}
