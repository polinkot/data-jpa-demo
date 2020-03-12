package com.example.datajpademo.repository;

import com.example.datajpademo.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID>, QuerydslPredicateExecutor<Category> {
}