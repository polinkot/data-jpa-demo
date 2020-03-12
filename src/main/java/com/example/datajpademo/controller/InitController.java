package com.example.datajpademo.controller;

import com.example.datajpademo.model.Category;
import com.example.datajpademo.model.Product;
import com.example.datajpademo.repository.CategoryRepository;
import com.example.datajpademo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static java.util.Arrays.asList;

@RequestMapping("/init")
@RestController
public class InitController {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public void init() {
        categoryRepository.deleteAll();

        Timestamp ts = Timestamp.from(Instant.now());

        List<Category> categories = categoryRepository.saveAll(asList(
                Category.builder().name("category1").ts(ts).build(),
                Category.builder().name("category2").ts(ts).build(),
                Category.builder().name("category3").ts(ts).build()));

        productRepository.saveAll(asList(
                Product.builder().name("product124").ts(ts).categoryId(categories.get(0).getId()).build(),
                Product.builder().name("product135").ts(ts).categoryId(categories.get(0).getId()).build(),
                Product.builder().name("product224").ts(ts).categoryId(categories.get(1).getId()).build(),
                Product.builder().name("product235").ts(ts).categoryId(categories.get(1).getId()).build()
        ));
    }
}