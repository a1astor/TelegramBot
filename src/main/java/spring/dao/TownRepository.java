package spring.dao;

import org.springframework.data.repository.CrudRepository;
import spring.model.Town;

import java.util.Optional;

public interface TownRepository extends CrudRepository<Town, Long> {

    Optional<Town> findByName(String name);

}
