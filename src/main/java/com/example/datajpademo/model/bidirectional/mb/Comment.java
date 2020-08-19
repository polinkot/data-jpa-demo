package com.example.datajpademo.model.bidirectional.mb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.SEQUENCE;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment {
    @Id
    @GeneratedValue(generator = "comment_sequence", strategy = SEQUENCE)
    @SequenceGenerator(name = "comment_sequence", allocationSize = 10)
    private Long id;

    private String review;

    @JsonBackReference
    @ManyToOne(fetch = LAZY)
    private Post post;
}