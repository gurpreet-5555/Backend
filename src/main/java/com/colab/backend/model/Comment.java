package com.colab.backend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="content", nullable = false)
    private String content;

//    @Column(name="post_id", nullable = false)
//    private Integer post_id;

    @Column(name="created_at", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @ManyToOne(optional = false)
    @JoinColumn(name="user_id")
    private User author;

    @ManyToOne(optional = false)
    @JoinColumn(name="post_id")
    @JsonBackReference
    private Post post;

    public Comment(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public Integer getPost_id() {
//        return post_id;
//    }
//
//    public void setPost_id(Integer post_id) {
//        this.post_id = post_id;
//    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
