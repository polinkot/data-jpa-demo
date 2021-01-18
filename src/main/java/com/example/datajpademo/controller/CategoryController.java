package com.example.datajpademo.controller;

import com.example.datajpademo.model.Category;
import com.example.datajpademo.model.Product;
import com.example.datajpademo.model.dto.CategoryWithPageableProductsView;
import com.example.datajpademo.model.dto.CategoryWithProductsView;
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
import java.util.List;
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

    /************************* With products ***************************/

    @ApiOperation(value = "Получение по id со списком продуктов (2 продукта)")
    @GetMapping("/{id}/withProducts")
    public CategoryWithProductsView findByIdWithProducts(@PathVariable("id") UUID categoryId) {
        return repository.findById(categoryId, CategoryWithProductsView.class).stream().findFirst().orElse(null);
    }

    @ApiOperation(value = "Получение по id с постраничным списком продуктов")
    @GetMapping("/{id}/withPageableProducts")
    public CategoryWithPageableProductsView findByIdWithPageableProducts(@PathVariable("id") UUID id, Pageable productsPageable) {
        return repository.findById(id, CategoryWithPageableProductsView.class).stream()
                .peek(category -> category.getProducts().addAll(productRepository.findByCategoryId(id, productsPageable)))
                .findFirst()
                .orElse(null);
    }

    /************************* Get products separately ***************************/

    /** 1. In Product controller with @QuerydslPredicate(root = Product.class) Predicate predicate **/
    /**
     * http://localhost:9090/products?categoryId={id}
     **/

    @GetMapping("/{id}/products")
    public List<Product> getProducts(@PathVariable("id") UUID categoryId, Pageable pageable) {
        /** 2. In repository repository.findByCategoryId(categoryId) **/
        return productRepository.findByCategoryId(categoryId, pageable);
    }

    @GetMapping("/{id}/productsDsl")
    public Iterable<Product> findProductsDsl(@PathVariable("id") UUID categoryId, Pageable pageable) {
        /** 3. With DSL repository.findAll(product.categoryId.eq(categoryId)) **/
        return productRepository.findAll(product.categoryId.eq(categoryId), pageable);
    }

    /************************* Set products ***************************/

    @PostMapping("/{id}/products")
    public List<Product> setProducts(@PathVariable("id") UUID id, @RequestBody Set<Product> products) {
        Set<UUID> productIds = products.stream().map(Product::getId).collect(toSet());

        List<Product> existing = productRepository.findByCategoryId(id);
        existing.removeIf(product -> productIds.contains(product.getId()));
        productRepository.deleteInBatch(existing);

        productRepository.saveAll(products.stream().filter(product -> product.getCategoryId().equals(id)).collect(toSet()));
        return productRepository.findByCategoryId(id);
    }
}