package com.example.datajpademo.repository.base;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Polina Kotelnikova
 */
class BaseJdbcRepositoryImpl implements BaseJdbcRepository {

    @Getter
    @Autowired
    private JdbcTemplate jdbcTemplate;
}