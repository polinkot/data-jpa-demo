package com.example.datajpademo.repository.bidirectional.identityinfo;

import com.example.datajpademo.model.bidirectional.identityinfo.Catalog;
import com.example.datajpademo.model.bidirectional.identityinfo.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface CatalogRepository extends JpaRepository<Catalog, Long>, QuerydslPredicateExecutor<Catalog> {
    @Query("select i from Item i ")
    List<Item> findItems();
}