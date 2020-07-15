package com.example.datajpademo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import static com.fasterxml.jackson.annotation.JsonProperty.Access.WRITE_ONLY;
import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PostComment {

    @Id
    @GeneratedValue
    private Long id;

    private String review;

    @JsonProperty(access = WRITE_ONLY)
    @ManyToOne(fetch = LAZY)
    private Post post;
}