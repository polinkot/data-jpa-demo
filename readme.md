# Демо проект по работе с данными в приложении.

В проекте используется в основном [Spring Data JPA](https://spring.io/projects/spring-data-jpa).

Spring Data JPA значительно упрощает работу с данными в приложении. Уменьшает количество рутинного шаблонного кода. На уровне работы с данными необходимо создать для сущности  только интерфейс, расширяющий один из репозиториев (например, JpaRepository). Spring обнаруживает интерфейс репозитория и создаёт для него реализацию. Автоматически создаётся готовый набор самых необходимых стандартных CRUD методов. В JPA-репозиториях можно создавать любые необходимые кастомные методы, в том числе с использованием JdbcTemplate. Есть поддержка [Querydsl](http://www.querydsl.com/).

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
Используется [Spring Data Querydsl Web Support](https://www.baeldung.com/rest-api-search-querydsl-web-in-spring-data-jpa). 
Автоматически собирает Querydsl предикат из полей сущности, заданных в реквесте, получим готовый предикат в параметре контроллера и сразу можем передать предикат в метод репозитория (например, findAll).  
Пример запроса `http://localhost:9090/products?name=product3&code=code3` 
```
    @ApiOperation(value = "Получение по id")
    @GetMapping("/{id}")
    public Product findById(@PathVariable UUID id) throws Exception {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(id));
    }

```
```
    @ApiOperation(value = "Получение по id (Web Support)")
    @GetMapping("/web_support/{id}")
    public Product findById1(@PathVariable("id") Product product) throws Exception {
        return ofNullable(product).orElseThrow(Exception::new);
    }
```
Используется [Spring Data Web Support](https://www.baeldung.com/spring-data-web-support). Spring автоматически преобразует path variable {id} к типу id класса Product (UUID) и получает из репозитория соответсвующий объект Product. Но в этом случае не можем получить сам id (например, чтобы залогировать, если объект не найден, или передать в Exception).  
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
