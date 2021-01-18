package com.example.datajpademo.controller;

import com.example.datajpademo.model.Country;
import com.example.datajpademo.model.dto.CountryBigProjection;
import com.example.datajpademo.model.dto.CountrySmallProjection;
import com.example.datajpademo.model.dto.View.Basic;
import com.example.datajpademo.model.dto.View.Quick;
import com.example.datajpademo.repository.CountryRepository;
import com.fasterxml.jackson.annotation.JsonView;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/countries")
@RestController
public class CountryController {

    @Autowired
    private CountryRepository repository;

    /*************** CRUD *******************/

    @JsonView(Basic.class)
    @ApiOperation(value = "Получение всех")
    @GetMapping
    public List<Country> findAll(@QuerydslPredicate(root = Country.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable).getContent();
    }

    @JsonView(Quick.class)
    @ApiOperation(value = "Получение всех (Quick view)")
    @GetMapping("/quick")
    public List<Country> findAllQuick(@QuerydslPredicate(root = Country.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable).getContent();
    }

    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public Country findById(@PathVariable("id") Country country) throws Exception {
        return ofNullable(country).orElseThrow(Exception::new);
    }

    @ApiOperation(value = "Создание")
    @PostMapping
    @ResponseStatus(CREATED)
    public @Valid Country create(@RequestBody @Valid Country country) {
        return repository.save(country);
    }

    @ApiOperation(value = "Обновление")
    @PutMapping
    public @Valid Country update(@RequestBody @Valid Country country) {
        return repository.save(country);
    }

    @ApiOperation(value = "Удаление")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }

    /*************** Dynamic projections *******************/

    @ApiOperation(value = "Получение всех. Динамические проекции.")
    @GetMapping("/dynamic")
    public List<Country> findBy(Pageable pageable) {
        return repository.findBy(pageable, Country.class);
    }

    @ApiOperation(value = "Получение всех. Динамические проекции. Big")
    @GetMapping("/dynamicBig")
    public List<CountryBigProjection> findBy1(Pageable pageable) {
        return repository.findBy(pageable, CountryBigProjection.class);
    }

    @ApiOperation(value = "Получение всех. Динамические проекции. Small")
    @GetMapping("/dynamicSmall")
    public List<CountrySmallProjection> findBy2(Pageable pageable) {
        return repository.findBy(pageable, CountrySmallProjection.class);
    }
}