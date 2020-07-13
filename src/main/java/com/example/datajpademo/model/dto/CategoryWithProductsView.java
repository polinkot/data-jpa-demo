package com.example.datajpademo.model.dto;

import com.example.datajpademo.model.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

@JsonPropertyOrder({"id", "name", "ts", "productsLimited", "allProducts"})
public interface CategoryWithProductsView {

    UUID getId();

    String getName();

    Timestamp getTs();

    @Value("#{@productRepository.findByCategoryId(target.getId(), T(org.springframework.data.domain.PageRequest).of(0, 2))}")
    List<Product> getProductsLimited();

    @Value("#{@productRepository.findByCategoryId(target.getId())}")
    List<Product> getAllProducts();
}