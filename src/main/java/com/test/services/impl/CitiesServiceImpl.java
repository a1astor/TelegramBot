package com.test.services.impl;

import com.test.dao.CitiesRepository;
import com.test.exceptions.ItemAlreadyExistException;
import com.test.exceptions.NoSuchCityException;
import com.test.model.City;
import com.test.services.CitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CitiesServiceImpl implements CitiesService {

    private CitiesRepository repository;

    @Override
    public Iterable<City> findAll() {
        return repository.findAll();
    }

    @Override
    public City findById(Long id) throws NoSuchCityException {
        Optional<City> city = repository.findById(id);
        if (city.isEmpty()) {
            throw new NoSuchCityException();
        }
        return city.get();
    }

    @Override
    public City findByName(String name) throws NoSuchCityException {
        Optional<City> city = repository.findByName(name);
        if (city.isEmpty()) {
            throw new NoSuchCityException();
        }
        return city.get();
    }

    @Override
    public City save(String name) throws ItemAlreadyExistException {
        Optional<City> cityToAdd = repository.findByName(name);
        if (cityToAdd.isPresent()) {
            throw new ItemAlreadyExistException();
        }
        return repository.save(new City(null,name));
    }

    public void delete(City city) {
        repository.delete(city);
    }

    public void deleteById(Long id) throws NoSuchCityException {
        Optional<City> deletingCity = repository.findById(id);
        if (deletingCity.isEmpty()) {
            throw new NoSuchCityException();
        }
        repository.deleteById(id);
    }

    @Override
    public void deleteByName(String name) throws NoSuchCityException {
        Optional<City> deletingCity = repository.findByName(name);
        if (deletingCity.isEmpty()) {
            throw new NoSuchCityException();
        }
        repository.deleteByName(name);
    }

    @Autowired
    public void setRepository(CitiesRepository repository) {
        this.repository = repository;
    }
}
