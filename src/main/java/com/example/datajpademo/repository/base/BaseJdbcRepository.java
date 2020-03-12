package com.example.datajpademo.repository.base;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Polina Kotelnikova
 */
public interface BaseJdbcRepository {
    JdbcTemplate getJdbcTemplate();
}