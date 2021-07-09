package com.superhero.repository;

import com.superhero.SuperHerosApplication;
import com.superhero.model.SuperHero;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
@DataJpaTest(excludeAutoConfiguration =  FlywayAutoConfiguration.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(classes = {SuperHerosApplication.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SuperHeroIT {

    @Autowired
    private SuperHeroRepository superHeroRepository;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Order(1)
    @Test
    void saveAndRetrieveASuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);
        superHero.setId(1L);

        superHeroRepository.save(superHero);

        Optional<SuperHero> superHeroRetrieved = superHeroRepository.findById(1L);

        Assertions.assertEquals(superHero.getName(), superHeroRetrieved.get().getName());
        Assertions.assertEquals(superHero.getSuperHeroName(), superHeroRetrieved.get().getSuperHeroName());
        Assertions.assertEquals(superHero.getDescription(), superHeroRetrieved.get().getDescription());
        Assertions.assertEquals(superHero.getComic(), superHeroRetrieved.get().getComic());
    }

    @Order(2)
    @Test
    void updateASuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);

        String superHeroName2 = "SPIDERMAN";

        SuperHero superHeroSaved = superHeroRepository.save(superHero);

        superHero.setSuperHeroName(superHeroName2);

        SuperHero superHeroUpdate = superHeroRepository.save(superHero);

        Assertions.assertEquals(superHeroName2, superHeroUpdate.getSuperHeroName());
        Assertions.assertEquals(name, superHeroSaved.getName());
        Assertions.assertEquals(description, superHeroSaved.getDescription());
        Assertions.assertEquals(comic, superHeroSaved.getComic());
    }

    @Order(3)
    @Test
    void deleteASuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);
        superHero.setId(1L);

        superHeroRepository.delete(superHero);

        Optional<SuperHero> superHeroRetrieved = superHeroRepository.findById(1L);

        Assertions.assertEquals(Optional.empty(), superHeroRetrieved);
    }

}
