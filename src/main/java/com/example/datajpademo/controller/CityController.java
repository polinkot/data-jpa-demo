package com.example.datajpademo.controller;

import com.example.datajpademo.model.City;
import com.example.datajpademo.repository.CityRepository;
import com.querydsl.core.types.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.CREATED;

@RequestMapping("/cities")
@RestController
public class CityController {
    @Autowired
    private CityRepository repository;

    /*************** CRUD *******************/

    @ApiOperation(value = "Получение всех")
    @GetMapping
    public Page<City> findAll(@QuerydslPredicate(root = City.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable);
    }

    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public City findById(@PathVariable("id") City city) throws Exception {
        return ofNullable(city).orElseThrow(Exception::new);
    }

    @ApiOperation(value = "Создание")
    @PostMapping
    @ResponseStatus(CREATED)
    public @Valid City create(@RequestBody @Valid City city) {
        return repository.save(city);
    }

    @ApiOperation(value = "Обновление")
    @PutMapping
    public @Valid City update(@RequestBody @Valid City city) {
        return repository.save(city);
    }

    @ApiOperation(value = "Удаление")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}