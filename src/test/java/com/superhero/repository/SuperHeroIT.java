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

import java.util.List;
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

    @Order(4)
    @Test
    void findBySuperHeroNameCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);

        String name2 = "Clark Kent";
        String superHeroName2 = "Superman";
        String description2 = "Fuerza sobrehumana, velocidad, resistencia, agilidad, reflejos, durabilidad, sentidos y longevidad. Poderes oculares. Agudeza sobrehumana. " +
                "Visión de calor. Visión del espectro electromagnético. Visión microscópica. Invulnerabilidad. Volar";
        String comic2 = "Marvel";
        SuperHero superHero2 = new SuperHero(name2, superHeroName2, description2, comic2);

        String name3 = "Bruce Banner";
        String superHeroName3 = "Hulk";
        String description3 = "Invulnerabilidad. Longevidad. Regeneración. Capacidad de respirar bajo el agua, respirar en el espacio" +
                "y ver fantasmas y otras entidades astrales.";
        String comic3 = "Marvel";
        SuperHero superHero3 = new SuperHero(name3, superHeroName3, description3, comic3);

        superHeroRepository.save(superHero);
        superHeroRepository.save(superHero2);
        superHeroRepository.save(superHero3);

        List<SuperHero> superHeroesList = superHeroRepository.findBySuperHeroNameContaining("man");

        Assertions.assertEquals(2, superHeroesList.size());
        Assertions.assertEquals(superHero.getName(), superHeroesList.get(0).getName());
        Assertions.assertEquals(superHero.getSuperHeroName(), superHeroesList.get(0).getSuperHeroName());
        Assertions.assertEquals(superHero.getDescription(), superHeroesList.get(0).getDescription());
        Assertions.assertEquals(superHero.getComic(), superHeroesList.get(0).getComic());
        Assertions.assertEquals(superHero2.getName(), superHeroesList.get(1).getName());
        Assertions.assertEquals(superHero2.getSuperHeroName(), superHeroesList.get(1).getSuperHeroName());
        Assertions.assertEquals(superHero2.getDescription(), superHeroesList.get(1).getDescription());
        Assertions.assertEquals(superHero2.getComic(), superHeroesList.get(1).getComic());
    }

}
