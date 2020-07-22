package com.example.datajpademo.model.bidirectional.mb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class PostComment {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String review;

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    private Post post;
}