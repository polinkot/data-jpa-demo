package com.example.datajpademo.repository;

import com.example.datajpademo.model.Product;
import com.example.datajpademo.repository.base.BaseJdbcRepository;
import com.example.datajpademo.repository.base.BaseJinqRepository;
import com.example.datajpademo.repository.base.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID>, QuerydslPredicateExecutor<Product>, BaseRepository, BaseJinqRepository, BaseJdbcRepository {

    Collection<Product> findByCategoryId(@Param("categoryId") UUID categoryId);

    Collection<Product> findByName(@Param("name") String name);

    @Transactional
    void deleteByCategoryId(@Param("categoryId") UUID categoryId);

//    @Modifying
//    @Query(value = "DELETE FROM Product p WHERE p.categoryId = :categoryId AND p.id NOT IN :uuids")
//    void delete(@Param("categoryId") UUID categoryId, @Param("uuids") Collection<UUID> uuids);
//

    /*************** JPQL *******************/

    @Query("SELECT p FROM Product p WHERE p.name = :name")
    Collection<Product> jpqlFindByName(@Param("name") String name);

    /*************** Native *******************/

    @Query(value = "SELECT * FROM assd.test_product p WHERE p.name = :name", nativeQuery = true)
    Collection<Product> nativeFindByName(@Param("name") String name);

    /*************** JDBC *******************/
    RowMapper<Product> PRODUCT_ROW_MAPPER = new BeanPropertyRowMapper<>(Product.class);

    default List<Product> jdbcFindByName(@Param("name") String name) {
        return getJdbcTemplate().query("select * from assd.test_product p WHERE p.name = '" + name + "'", PRODUCT_ROW_MAPPER);
    }

    /*************** Jinq *******************/
    default List<Product> jinqFindByName(String name) {
        return getJinqDataProvider().streamAll(getEntityManager(), Product.class)
                .where(p -> p.getName().contains(name) || p.getName().contains("777777"))
                .toList();
    }
}