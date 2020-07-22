package com.example.datajpademo;

import com.example.datajpademo.model.Category;
import com.example.datajpademo.model.City;
import com.example.datajpademo.model.Country;
import com.example.datajpademo.model.Product;
import com.example.datajpademo.model.bidirectional.identityinfo.Catalog;
import com.example.datajpademo.model.bidirectional.identityinfo.Item;
import com.example.datajpademo.model.bidirectional.mb.Comment;
import com.example.datajpademo.model.bidirectional.mb.Post;
import com.example.datajpademo.repository.CategoryRepository;
import com.example.datajpademo.repository.CountryRepository;
import com.example.datajpademo.repository.ProductRepository;
import com.example.datajpademo.repository.bidirectional.identityinfo.CatalogRepository;
import com.example.datajpademo.repository.bidirectional.mb.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static java.util.Arrays.asList;

@SpringBootApplication
public class DataJpaDemoApplication implements ApplicationRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CatalogRepository catalogRepository;

    public static void main(String[] args) {
        SpringApplication.run(DataJpaDemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) {
        Post post = buildPost("First post");
        post.addComments(
                asList(buildComment("First comment"),
                        buildComment("Second comment"),
                        buildComment("Third comment")));
        postRepository.saveAll(asList(post, buildPost("Second post")));

        Catalog catalog = buildCatalog("First catalog");
        catalog.addItems(
                asList(buildItem("First item"),
                        buildItem("Second item"),
                        buildItem("Third item")));
        catalogRepository.saveAll(asList(catalog, buildCatalog("Second catalog")));

        countryRepository.saveAll(asList(
                buildCountry("India", "IN"),
                buildCountry("Brazil", "BR"),
                buildCountry("USA", "USA"),
                buildCountry("Italy", "IT",
                        new HashSet<>(asList(
                                buildCity("Rome"),
                                buildCity("Milan"),
                                buildCity("Bari")))
                )));

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

    private Catalog buildCatalog(String name) {
        return Catalog.builder().name(name).build();
    }

    private Item buildItem(String name) {
        return Item.builder().name(name).build();
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
