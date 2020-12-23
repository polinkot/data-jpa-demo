package com.example.datajpademo.model;

import com.example.datajpademo.model.dto.View.Basic;
import com.example.datajpademo.model.dto.View.Products;
import com.example.datajpademo.model.dto.View.Quick;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.CascadeType.ALL;

@JsonView(Basic.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {

    @JsonView({Basic.class, Quick.class})
    @Id
    @GeneratedValue
    private UUID id;

    @JsonView({Basic.class, Quick.class})
    private String name;

    private String shortName;

    private Long population;

    private Long area;

    @Builder.Default
    @JsonView(Products.class)
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "countryId")
    private Set<City> cities = new HashSet<>();
}
