package com.example.datajpademo.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"id", "review", "post"})
public interface CommentProjection {

    Long getId();

    String getReview();

    PostProjection getPost();

    interface PostProjection {
        Long getId();

        String getTitle();
    }
}