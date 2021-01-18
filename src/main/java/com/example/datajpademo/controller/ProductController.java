package com.example.datajpademo.controller;

import com.example.datajpademo.model.Product;
import com.example.datajpademo.repository.ProductRepository;
import com.example.datajpademo.service.ProductService;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static com.example.datajpademo.model.QProduct.product;
import static java.util.Optional.ofNullable;

@RequestMapping("/products")
@RestController
public class ProductController {
    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    /*************** CRUD *******************/

    @ApiOperation(value = "Получение всех")
    @GetMapping
    public Collection<Product> findAll(@QuerydslPredicate(root = Product.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable).getContent();
    }

    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public Product findById(@PathVariable("id") Product product) throws Exception {
        return ofNullable(product).orElseThrow(Exception::new);
    }

    @ApiOperation(value = "Создание")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @Valid Product create(@RequestBody @Valid Product product) {
        return repository.save(product);
    }

    @ApiOperation(value = "Обновление")
    @PutMapping
    public @Valid Product update(@RequestBody @Valid Product product) {
        return repository.save(product);
    }

    @ApiOperation(value = "Удаление")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }

    /*************** Batch *******************/

    @PostMapping("/list")
    public Collection<Product> saveAll(@RequestBody List<Product> products) {
        return repository.saveAll(products);
    }

    @DeleteMapping("/list")
    public void deleteAll(@RequestBody List<Product> products) {
        repository.deleteInBatch(products);
    }


    /*************** By Name *******************/

    @ApiOperation(value = "Получение по названию")
    @GetMapping("/byName/{name}")
    public Collection<Product> findByName(@PathVariable("name") String name) {
        return repository.findByName(name);
    }

    /*************** JPQL *******************/

    @GetMapping("/jpqlFindByName/{name}")
    public Collection<Product> jpqlFindByName(@PathVariable("name") String name) {
        return repository.jpqlFindByName(name);
    }

    /*************** Native *******************/

    @GetMapping("/nativeFindByName/{name}")
    public Collection<Product> nativeFindByName(@PathVariable("name") String name) {
        return repository.nativeFindByName(name);
    }

    /*************** JDBC *******************/

    @GetMapping("/jdbcFindByName/{name}")
    public Collection<Product> jdbcFindByName(@PathVariable("name") String name) {
        return repository.jdbcFindByName(name);
    }

    /*************** By example *******************/
    //https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example

    @ApiOperation(value = "By example")
    @GetMapping("/byExapmle")
    public List<Product> findByExample(Product product) {
        return service.findByExample(product);
    }

    /*************** DSL *******************/

    @GetMapping("/dslFindByName/{name}")
    public Iterable<Product> dslFindByName(@PathVariable("name") String name) {
        return repository.findAll(product.name.containsIgnoreCase(name));
    }

    /*************** Jinq *******************/

    @GetMapping("/jinqFindByName/{name}")
    public Collection<Product> jinqFindByName(@PathVariable("name") String name) {
        return repository.jinqFindByName(name);
    }
}