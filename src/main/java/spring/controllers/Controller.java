package spring.controllers;

import ch.qos.logback.core.html.IThrowableRenderer;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dao.TownRepository;
import spring.model.Town;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class Controller {

    private TownRepository townRepository;

    @ApiOperation(value = "Find all town")
    @GetMapping("/")
    public Iterable<Town> findAll() {
        return townRepository.findAll();
    }

    @ApiOperation(value = "Find town by id")
    @GetMapping("/{id}")
    public Town findTown(@PathVariable Long id) {
        return townRepository.findById(id).get();
    }

    @ApiOperation(value = "Creating town")
    @PostMapping("/town")
    public Town getMessage(String name) {
        Town savedTown = townRepository.save(new Town(null,name));
        return savedTown;
    }

    @Autowired
    public void setTownRepository(TownRepository townRepository) {
        this.townRepository = townRepository;
    }
}
