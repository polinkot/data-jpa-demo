package com.example.datajpademo.model.bidirectional.identityinfo;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.GenerationType.IDENTITY;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Catalog {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @Builder.Default
    @OneToMany(mappedBy = "catalog", cascade = ALL, orphanRemoval = true)
    private List<Item> items = new ArrayList<>();

    public void addItems(Collection<Item> items) {
        items.forEach(this::addItem);
    }

    public void addItem(Item item) {
        items.add(item);
        item.setCatalog(this);
    }

    public void removeItem(Item item) {
        items.remove(item);
        item.setCatalog(null);
    }
}