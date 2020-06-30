package com.example.datajpademo;

import com.example.datajpademo.model.City;
import com.example.datajpademo.model.Country;
import com.example.datajpademo.model.Post;
import com.example.datajpademo.model.PostComment;
import com.example.datajpademo.repository.CountryRepository;
import com.example.datajpademo.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.List;

import static java.util.Arrays.asList;

@SpringBootApplication
public class DataJpaDemoApplication implements ApplicationRunner {

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PostRepository postRepository;

    public static void main(String[] args) {
        SpringApplication.run(DataJpaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
//        Post post = Post.builder().title("First post").comments(
//                asList(PostComment.builder().review("My first review").build(),
//                        PostComment.builder().review("My second review").build(),
//                        PostComment.builder().review("My third review").build()
//                )).build();
//        postRepository.save(post);
//
//        List<Country> countries = asList(
//                Country.builder().name("India").shortName("IN").build(),
//                Country.builder().name("Brazil").shortName("BR").build(),
//                Country.builder().name("USA").shortName("USA").build(),
//                Country.builder().name("Italy").shortName("IT")
//                        .cities(new HashSet<>(asList(new City(null, "Rome", null),
//                                new City(null, "Milan", null),
//                                new City(null, "Bari", null))))
//                        .build());
//        countryRepository.saveAll(countries);

//        INSERT INTO country (id, name, short_name) VALUES ('19b9fb61-114b-42c6-9000-080f2dfda1f7', 'India', 'IN');
//        INSERT INTO country (id, name, short_name) VALUES ('ac812959-5c02-4228-926f-d03faf4604dc', 'Brazil', 'BR');
//        INSERT INTO country (id, name, short_name) VALUES ('570f7920-653a-4884-8de5-338884d26fdc', 'USA', 'USA');
//        INSERT INTO country (id, name, short_name) VALUES ('223b0d1e-9997-41a2-be0a-dd025faddb6e', 'Italy', 'IT');
//
//        INSERT INTO city (name, country_id) VALUES ('Rome', '223b0d1e-9997-41a2-be0a-dd025faddb6e');
//        INSERT INTO city (name, country_id) VALUES ('Milan', '223b0d1e-9997-41a2-be0a-dd025faddb6e');
//        INSERT INTO city (name, country_id) VALUES ('Bari', '223b0d1e-9997-41a2-be0a-dd025faddb6e');
//
//        INSERT INTO category (id, name) VALUES ('19b9fb61-114b-42c6-9000-080f2dfda1f8', 'category1');
//        INSERT INTO category (id, name) VALUES ('ac812959-5c02-4228-926f-d03faf4604d8', 'category2');
//
//        INSERT INTO product (name, category_id) VALUES ('product1', '19b9fb61-114b-42c6-9000-080f2dfda1f8');
//        INSERT INTO product (name, category_id) VALUES ('product2', '19b9fb61-114b-42c6-9000-080f2dfda1f8');
//        INSERT INTO product (name, category_id) VALUES ('product3', 'ac812959-5c02-4228-926f-d03faf4604d8');
    }
}
