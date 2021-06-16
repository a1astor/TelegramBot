package spring.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import spring.dao.TownRepository;
import spring.model.Town;

@RestController
public class Controller {

    private TownRepository townRepository;

    @GetMapping("/addtown")
    public Town getMessage(Town town) {
        Town savedTown = townRepository.save(town);
        return savedTown;
    }
}
