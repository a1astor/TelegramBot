package com.test.services;

import com.test.model.City;

public interface CitiesService {

    Iterable<City> findAll();

    City findById(Long id);

    City save(String name);

    void deleteById(Long id);

    void deleteByName(String name);

    City findByName(String name);

}
