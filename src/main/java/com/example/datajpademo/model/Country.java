package com.example.datajpademo.model;

import com.example.datajpademo.model.View.Basic;
import com.example.datajpademo.model.View.Products;
import com.example.datajpademo.model.View.Quick;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.example.datajpademo.config.AppConfig.UUID_PK_DEFAULT;
import static java.util.Collections.emptySet;
import static java.util.Optional.ofNullable;

@JsonView(Basic.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Country {

    @JsonView({Basic.class, Quick.class})
    @Id
    @GeneratedValue
    @ColumnDefault(UUID_PK_DEFAULT)
    private UUID id;

    @JsonView({Basic.class, Quick.class})
    private String name;

    private String shortName;

    private Long population;

    private Long area;

    @JsonView(Products.class)
    @JoinColumn(name = "country_id", updatable = false)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<City> cities = new HashSet<>();

    public void setCities(Set<City> cities) {
        this.cities.clear();
        ofNullable(cities).orElse(emptySet()).forEach(this::addCity);
    }

    public void addCity(@NonNull City city) {
        this.cities.add(city);
        city.setCountryId(this.id);
    }
}
