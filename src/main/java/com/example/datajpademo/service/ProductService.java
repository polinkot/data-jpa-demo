package com.example.datajpademo.service;

import com.example.datajpademo.model.Product;
import com.example.datajpademo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.matching;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public List<Product> findByExample(Product product) {
        ExampleMatcher matcher = matching().withMatcher("name", contains().ignoreCase());
        return repository.findAll(Example.of(product, matcher));
    }
}