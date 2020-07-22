package com.example.datajpademo.controller.bidirectional.identityinfo;

import com.example.datajpademo.model.bidirectional.identityinfo.Catalog;
import com.example.datajpademo.model.bidirectional.identityinfo.Item;
import com.example.datajpademo.repository.bidirectional.identityinfo.CatalogRepository;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/catalogs")
@RestController
public class CatalogController {

    @Autowired
    private CatalogRepository repository;

    /*************** CRUD *******************/

    @ApiOperation(value = "Получение всех")
    @GetMapping
    public List<Catalog> findAll(@QuerydslPredicate(root = Catalog.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable).getContent();
    }

    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public Catalog findById(@PathVariable("id") Catalog user) throws Exception {
        return ofNullable(user).orElseThrow(Exception::new);
    }

    @ApiOperation(value = "Создание")
    @PostMapping
    @ResponseStatus(CREATED)
    public @Valid Catalog create(@RequestBody @Valid Catalog catalog) {
        return repository.save(catalog);
    }

    @ApiOperation(value = "Обновление")
    @PutMapping
    public @Valid Catalog update(@RequestBody @Valid Catalog catalog) {
        return repository.save(catalog);
    }

    @ApiOperation(value = "Удаление")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }

    @GetMapping("/comments")
    public List<Item> findComments() {
        return repository.findItems();
    }
}