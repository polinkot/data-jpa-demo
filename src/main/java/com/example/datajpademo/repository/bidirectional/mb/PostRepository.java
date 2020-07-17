package com.example.datajpademo.repository.bidirectional.mb;

import com.example.datajpademo.model.bidirectional.mb.PostComment;
import com.example.datajpademo.model.bidirectional.mb.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>, QuerydslPredicateExecutor<Post> {
    @Query("select c from PostComment c ")
    List<PostComment> findComments();
}