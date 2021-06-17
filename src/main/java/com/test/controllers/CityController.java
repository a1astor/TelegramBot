package com.test.controllers;

import com.test.model.City;
import com.test.services.CitiesService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@RestController
@RequestMapping("/city")
@RequiredArgsConstructor
public class CityController {

    private CitiesService citiesService;

    @ApiOperation(value = "Find all cities")
    @GetMapping("/")
    public Iterable<City> findAll() {
        return citiesService.findAll();
    }

    @Pattern(regexp = "#^[0-9]+$#")
    @NotNull
    @ApiOperation(value = "Find city by id")
    @GetMapping("/byId{id}")
    public City findTown(@PathVariable Long id) {
        return citiesService.findById(id);
    }

    @NotNull
    @ApiOperation(value = "Find city by name")
    @GetMapping("/byName{name}")
    public City findCityByName(@PathVariable String name) {
        return citiesService.findByName(name);
    }

    @NotBlank
    @NotNull
    @ApiOperation(value = "Creating city by name")
    @PostMapping("/save")
    public City createCity(String name) {
        return citiesService.save(name);
    }

    @NotBlank
    @NotNull
    @ApiOperation(value = "Remove city")
    @Transactional
    @DeleteMapping("/remove{name}")
    public void removeCity(@PathVariable String name) {
        citiesService.deleteByName(name);
    }

    @Pattern(regexp = "#^[0-9]+$#")
    @NotNull
    @ApiOperation(value = "Remove city by id")
    @Transactional
    @DeleteMapping("/removeById{id}")
    public void removeCityById(@PathVariable Long id) {
        citiesService.deleteById(id);
    }

    @Autowired
    public void setCitiesService(CitiesService citiesService) {
        this.citiesService = citiesService;
    }
}
