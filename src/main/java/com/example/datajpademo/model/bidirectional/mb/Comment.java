package com.example.datajpademo.model.bidirectional.mb;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;

import java.sql.Timestamp;

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

    @Column(name = "created", updatable = false)
    @CreationTimestamp
    private Timestamp created;

    @Column(name = "modified")
    @UpdateTimestamp
    private Timestamp modified;
}