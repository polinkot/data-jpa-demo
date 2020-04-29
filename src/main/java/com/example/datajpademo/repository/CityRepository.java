package com.example.datajpademo.repository;

import com.example.datajpademo.model.City;
import com.example.datajpademo.model.QCity;
import com.example.datajpademo.repository.base.QuerydslCustomizer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.UUID;

public interface CityRepository extends JpaRepository<City, UUID>,
        QuerydslPredicateExecutor<City>, QuerydslCustomizer<QCity> {
}