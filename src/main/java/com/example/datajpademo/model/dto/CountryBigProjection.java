package com.example.datajpademo.model.dto;

import com.example.datajpademo.model.Country;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

@JsonPropertyOrder({"id", "countryName", "cities", "values"})
public interface CountryBigProjection {

    UUID getId();

    @Value("#{target.name} (#{target.shortName})")
    String getCountryName();

    Set<CityProjection> getCities();

    interface CityProjection {
        String getName();
    }

    @Value("#{new com.example.datajpademo.model.dto.CountryBigProjection.Values(target)}")
    Values getValues();

    @Getter
    class Values {
        private Map<String, Object> info = new LinkedHashMap<>();
        private List<Map<String, Object>> paths = new ArrayList<>();

        public Values(Country country) {
            info.put("countryArea", country.getArea());
            info.put("countryPopulation", country.getPopulation());

            country.getCities().forEach(city -> {
                Map<String, Object> item = new LinkedHashMap<>();
                item.put("cityId", city.getId());
                item.put("path", country.getName() + " (" + country.getShortName() + ") " + city.getName());
                paths.add(item);
            });
        }
    }
}