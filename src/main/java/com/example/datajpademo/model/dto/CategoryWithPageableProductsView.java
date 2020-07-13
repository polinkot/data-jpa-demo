package com.example.datajpademo.model.dto;

import com.example.datajpademo.model.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Getter
@RequiredArgsConstructor
@JsonPropertyOrder({"id", "name", "ts", "products"})
public class CategoryWithPageableProductsView {

    private final UUID id;

    private final String name;

    private final Date ts;

    @Setter
    private List<Product> products = new ArrayList<>();
}