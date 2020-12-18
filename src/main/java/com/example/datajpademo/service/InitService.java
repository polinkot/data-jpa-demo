package com.example.datajpademo.service;

import com.example.datajpademo.model.Category;
import com.example.datajpademo.model.City;
import com.example.datajpademo.model.Country;
import com.example.datajpademo.model.Product;
import com.example.datajpademo.model.bidirectional.mb.Comment;
import com.example.datajpademo.model.bidirectional.mb.Post;
import com.example.datajpademo.repository.CategoryRepository;
import com.example.datajpademo.repository.CountryRepository;
import com.example.datajpademo.repository.ProductRepository;
import com.example.datajpademo.repository.bidirectional.mb.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.lang.System.currentTimeMillis;
import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Stream.iterate;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
public class InitService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PostRepository postRepository;

    public void init() {
        initPostComments();
        initCountryCity();
        initCategoryProducts();
    }

    public void initPostComments() {
        for (int i = 0; i < 3; i++) {
            Post post = buildPost("First post" + currentTimeMillis());
            post.addComments(
                    asList(buildComment("First comment"),
                            buildComment(randomAlphanumeric(10)),
                            buildComment(randomAlphanumeric(15)),
                            buildComment(randomAlphanumeric(5)),
                            buildComment("Second comment"),
                            buildComment("Third comment")));

            Post post3 = buildPost("3 post" + currentTimeMillis());
            post3.addComments(
                    iterate(0, n -> n).limit(5).map(n -> buildComment(randomAlphanumeric(3))).collect(toList())
            );

            postRepository.saveAll(asList(post, buildPost("Second post" + currentTimeMillis()), post3));
        }
    }

    public void initCountryCity() {
        countryRepository.saveAll(asList(
                buildCountry("India", "IN"),
                buildCountry("Brazil", "BR"),
                buildCountry("USA", "USA",
                        iterate(0, n -> n).limit(4).map(n -> buildCity(randomAlphanumeric(10))).collect(toSet())
                ),
                buildCountry("Italy", "IT",
                        new HashSet<>(asList(
                                buildCity("Rome"),
                                buildCity("Milan"),
                                buildCity("Bari")))
                )));
    }

    public void initCategoryProducts() {
        Category category1 = buildCategory("category1");
        Category category2 = buildCategory("category2");
        categoryRepository.saveAll(asList(category1, category2));

        productRepository.saveAll(asList(
                buildProduct("product1", category1.getId()),
                buildProduct("product2", category1.getId()),
                buildProduct("product3", category1.getId()),
                buildProduct("product4", category2.getId())));
    }

    private Category buildCategory(String name) {
        return Category.builder().name(name).build();
    }

    private Product buildProduct(String name, UUID categoryId) {
        return Product.builder().name(name).categoryId(categoryId).build();
    }

    private Post buildPost(String title) {
        return Post.builder().title(title).build();
    }

    private Comment buildComment(String review) {
        return Comment.builder().review(review).build();
    }

    private Country buildCountry(String name, String shortName) {
        return buildCountry(name, shortName, new HashSet<>());
    }

    private Country buildCountry(String name, String shortName, Set<City> cities) {
        return Country.builder().name(name).shortName(shortName).cities(cities).build();
    }

    private City buildCity(String name) {
        return City.builder().name(name).build();
    }
}