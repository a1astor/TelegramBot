package spring;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.dao.TownRepository;

@Service
@NoArgsConstructor
public class Services {

    private TownRepository townRepository;


    @Autowired
    public void setTownRepository(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public TownRepository getTownRepository() {
        return townRepository;
    }
}
