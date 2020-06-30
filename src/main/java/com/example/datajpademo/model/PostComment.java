package com.example.datajpademo.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Getter
@Setter
@Builder
@Entity
public class PostComment {

    @Id
    @GeneratedValue
    private Long id;

    private String review;
}