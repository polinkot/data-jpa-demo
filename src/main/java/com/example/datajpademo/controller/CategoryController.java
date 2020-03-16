package com.example.datajpademo.controller;

import com.example.datajpademo.model.Category;
import com.example.datajpademo.model.Product;
import com.example.datajpademo.repository.CategoryRepository;
import com.example.datajpademo.repository.ProductRepository;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static com.example.datajpademo.model.QProduct.product;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.toSet;

@RequestMapping("/categories")
@RestController
public class CategoryController {
    @Autowired
    private CategoryRepository repository;

    @Autowired
    private ProductRepository productRepository;

    /*************** CRUD *******************/

    @ApiOperation(value = "Получение всех")
    @GetMapping
    public Page<Category> findAll(@QuerydslPredicate(root = Category.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public Category findById(@PathVariable("id") Category category) throws Exception {
        return ofNullable(category).orElseThrow(Exception::new);
    }

    @ApiOperation(value = "Создание")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public @Valid Category create(@RequestBody @Valid Category category) {
        return repository.save(category);
    }

    @ApiOperation(value = "Обновление")
    @PutMapping
    public @Valid Category update(@RequestBody @Valid Category category) {
        return repository.save(category);
    }

    @ApiOperation(value = "Удаление")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }

    /************************* Get products ***************************/

    /** 1. In Product controller with @QuerydslPredicate(root = Product.class) Predicate predicate **/
    /**
     * http://localhost:8080/products?categoryId={id}
     **/

    @GetMapping("/{id}/products")
    public Collection<Product> getProducts(@PathVariable("id") UUID categoryId) {
        /** 2. In repository repository.findByCategoryId(categoryId) **/
        return productRepository.findByCategoryId(categoryId);
    }

    @GetMapping("/{id}/productsDsl")
    public Iterable<Product> findProductsDsl(@PathVariable("id") UUID categoryId) {
        /** 3. With DSL repository.findAll(product.categoryId.eq(categoryId)) **/
        return productRepository.findAll(product.categoryId.eq(categoryId));
    }

    /************************* Set products ***************************/

    @PostMapping("/{id}/products")
    public Collection<Product> setProducts(@PathVariable("id") UUID id, @RequestBody Set<Product> products) {
        Set<UUID> productIds = products.stream().map(Product::getId).collect(toSet());

        Collection<Product> existing = productRepository.findByCategoryId(id);
        existing.removeIf(product -> productIds.contains(product.getId()));
        productRepository.deleteInBatch(existing);

        productRepository.saveAll(products.stream().filter(product -> product.getCategoryId().equals(id)).collect(toSet()));
        return productRepository.findByCategoryId(id);
    }
}