package com.example.datajpademo.repository;

import com.example.datajpademo.model.Country;
import com.example.datajpademo.model.QCountry;
import com.example.datajpademo.repository.base.QuerydslCustomizer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;
import java.util.UUID;

public interface CountryRepository extends JpaRepository<Country, UUID>,
        QuerydslPredicateExecutor<Country>, QuerydslCustomizer<QCountry> {
    <T> List<T> findBy(Pageable pageable, Class<T> tClass);
}