package com.example.datajpademo.repository.base;

import lombok.Getter;
import org.jinq.jpa.JinqJPAStreamProvider;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Polina Kotelnikova
 */
class BaseJinqRepositoryImpl implements BaseJinqRepository {

    @Getter
    @Autowired
    private JinqJPAStreamProvider jinqDataProvider;
}