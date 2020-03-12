package com.example.datajpademo.repository.base;

import javax.persistence.EntityManager;

/**
 * @author Polina Kotelnikova
 */
public interface BaseRepository {
    EntityManager getEntityManager();
}