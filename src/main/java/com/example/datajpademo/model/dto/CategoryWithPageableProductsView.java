package com.example.datajpademo.model.dto;

import com.example.datajpademo.model.Product;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Value
@JsonPropertyOrder({"id", "name", "ts", "products"})
public class CategoryWithPageableProductsView {
    UUID id;
    String name;
    Date ts;

    List<Product> products = new ArrayList<>();
}