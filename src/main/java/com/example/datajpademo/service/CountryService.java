package com.example.datajpademo.service;

import com.example.datajpademo.model.City;
import com.example.datajpademo.model.Country;
import com.example.datajpademo.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.contains;
import static org.springframework.data.domain.ExampleMatcher.matching;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repository;

    public Collection<City> getCapitals(Country country) {
        return country.getCities().stream().filter(City::isCapital).collect(toList());
    }

    public List<Country> findByExample(Country country) {
        ExampleMatcher matcher = matching().withMatcher("name", contains().ignoreCase());
        return repository.findAll(Example.of(country, matcher));
    }
}