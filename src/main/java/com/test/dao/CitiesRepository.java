package com.test.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.test.model.City;

import java.util.Optional;

@Repository
public interface CitiesRepository extends CrudRepository<City, Long> {

    Optional<City> findByName(String name);

    void deleteByName(String name);
}
