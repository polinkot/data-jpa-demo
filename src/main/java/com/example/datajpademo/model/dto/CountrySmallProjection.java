package com.example.datajpademo.model.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.beans.factory.annotation.Value;

@JsonPropertyOrder({"countryName", "area"})
public interface CountrySmallProjection {

    @Value("#{target.name} - #{target.shortName}")
    String getCountryName();

    Long getArea();

    @Value("#{target.cities.size()}")
    Integer getCitiesCount();
}