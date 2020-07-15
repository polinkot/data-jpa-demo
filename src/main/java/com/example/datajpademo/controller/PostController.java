package com.example.datajpademo.controller;

import com.example.datajpademo.model.Post;
import com.example.datajpademo.repository.PostRepository;
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

@RequestMapping("/posts")
@RestController
public class PostController {
    @Autowired
    private PostRepository repository;

    /*************** CRUD *******************/

    @ApiOperation(value = "Получение всех")
    @GetMapping
    public List<Post> findAll(@QuerydslPredicate(root = Post.class) Predicate predicate, Pageable pageable) {
        return repository.findAll(predicate, pageable).getContent();
    }

    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public Post findById(@PathVariable("id") Post post) throws Exception {
        return ofNullable(post).orElseThrow(Exception::new);
    }

    @ApiOperation(value = "Создание")
    @PostMapping
    @ResponseStatus(CREATED)
    public @Valid Post create(@RequestBody @Valid Post post) {
        return repository.save(post);
    }

    @ApiOperation(value = "Обновление")
    @PutMapping
    public @Valid Post update(@RequestBody @Valid Post post) {
        return repository.save(post);
    }

    @ApiOperation(value = "Удаление")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        repository.deleteById(id);
    }
}