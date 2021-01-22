# Демо проект по работе с данными в приложении.

В проекте используется в основном [Spring Data JPA](https://spring.io/projects/spring-data-jpa).

Spring Data JPA значительно упрощает работу с данными в приложении. Уменьшает количество рутинного шаблонного кода. На уровне работы с данными необходимо создать для сущности  только интерфейс, расширяющий один из репозиториев (например JpaRepository). Spring Data обнаруживает интерфейс репозитория и создаёт для него реализацию. Автоматически создаётся готовый набор самых необходимых стандартных CRUD методов. В JPA-репозиториях можно создавать любые необходимые кастомные методы, в том числе с использованием JdbcTemplate. Есть поддержка [Querydsl](http://www.querydsl.com/).

В нескольких методах используется [Jinq](http://www.jinq.org/).

В проекте реализованы примеры для нескольких пар сущностей. Все пары на уровне БД связаны соотношением One-to-Many. Рассматриваются различные варианты доступа к данным.

## Category Product
Не связаны на уровне JPA.
```
@Entity
public class Category {

    @Id
    private UUID id;

    private String name;
}
```
```
@Entity
public class Product {

    @Id
    private UUID id;

    private String name;

    @NotNull
    @Column(nullable = false, updatable = false)
    private UUID categoryId;
}
```
### Методы ProductController

#### CRUD
```
@ApiOperation(value = "Получение всех")
@GetMapping
public Collection<Product> findAll(@QuerydslPredicate(root = Product.class) Predicate predicate, Pageable pageable) {
    return repository.findAll(predicate, pageable).getContent();
}
```
```
@ApiOperation(value = "Получение по id")
@GetMapping("/{id}")
public Product findById(@PathVariable("id") Product product) throws Exception {
    return ofNullable(product).orElseThrow(Exception::new);
}
```
```
@ApiOperation(value = "Создание")
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public @Valid Product create(@RequestBody @Valid Product product) {
    return repository.save(product);
}
```
```
@ApiOperation(value = "Обновление")
@PutMapping
public @Valid Product update(@RequestBody @Valid Product product) {
    return repository.save(product);
}
```
```
@ApiOperation(value = "Удаление")
@DeleteMapping("/{id}")
public void delete(@PathVariable UUID id) {
    repository.deleteById(id);
}
```

## Country City
Связаны на уровне JPA с помощью unidirectional `@OneToMany`
```
@JsonView(Basic.class)
@Entity
public class Country {

    @JsonView({Basic.class, Quick.class})
    @Id
    private UUID id;

    @JsonView({Basic.class, Quick.class})
    private String name;

    @JsonView(Products.class)
    @OneToMany(cascade = ALL, orphanRemoval = true)
    @JoinColumn(name = "countryId")
    private Set<City> cities = new HashSet<>();
}
```
```
@Entity
public class City {

    @Id
    private UUID id;

    private String name;
    private UUID countryId;
}
```

## Post Comment
Связаны на уровне JPA с помощью bidirectional `@OneToMany`
```
@Entity
public class Post {

    @Id
    private Long id;

    private String title;

    @OneToMany(mappedBy = "post", cascade = ALL, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();
}
```
```
@Entity
public class Comment {
    @Id
    private Long id;

    private String review;

    @ManyToOne(fetch = LAZY)
    private Post post;
}
```
