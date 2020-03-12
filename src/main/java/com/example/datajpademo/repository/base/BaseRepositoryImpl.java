package com.example.datajpademo.repository.base;

import lombok.Getter;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Polina Kotelnikova
 */
class BaseRepositoryImpl implements BaseRepository {

    @Getter
    @PersistenceContext
    private EntityManager entityManager;
}