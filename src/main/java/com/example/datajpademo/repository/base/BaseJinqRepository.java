package com.example.datajpademo.repository.base;

import org.jinq.jpa.JinqJPAStreamProvider;

/**
 * @author Polina Kotelnikova
 */
public interface BaseJinqRepository {

    JinqJPAStreamProvider getJinqDataProvider();
}