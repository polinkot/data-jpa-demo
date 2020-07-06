package com.example.datajpademo.service;

import com.example.datajpademo.model.City;
import com.example.datajpademo.model.Country;
import org.springframework.stereotype.Service;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
public class CountryService {
    public Collection<City> getCapitals(Country country) {
        return country.getCities().stream().filter(City::isCapital).collect(toList());
    }
}