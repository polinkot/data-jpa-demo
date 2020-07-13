package com.example.datajpademo.repository;

import com.example.datajpademo.model.Category;
import com.example.datajpademo.model.QCategory;
import com.example.datajpademo.repository.base.QuerydslCustomizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>,
        QuerydslPredicateExecutor<Category>, QuerydslCustomizer<QCategory> {
    <T> List<T> findById(@Param("id") UUID id, Class<T> tClass);
}