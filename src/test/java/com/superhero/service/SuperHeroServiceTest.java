package com.superhero.service;

import com.superhero.model.SuperHero;
import com.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class SuperHeroServiceTest {
    @Mock
    SuperHeroRepository superHeroRepository;

    @InjectMocks
    SuperHeroService superHeroService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findAllWithShouldReturnAListOfSuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "'Marvel'";
        SuperHero superHero1 = new SuperHero(name, superHeroName, description, comic);
        String name2 = "Steve Rogers";
        String superHeroName2 = "Captain America";
        String description2 = "Sentidos, agilidad, velocidad y fuerza sobrehumanas. Gran habilidad con armas de fuego, con el martillo mjolnir y con su escudo. " +
                "Instinto de liderazgo, gran resistencia, inmune a gases y enfermedades, curación y regeneración acelerada. Genio táctico, artista marcial," +
                "acróbata experto";
        String comic2 = "'Marvel'";
        SuperHero superHero2 = new SuperHero(name2, superHeroName2, description2, comic2);

        List<SuperHero> superHeroList = Stream.of(superHero1, superHero2).collect(Collectors.toList());

        Mockito.when(superHeroRepository.findAll()).thenReturn(superHeroList);

        List<SuperHero> superHeroListResponse = superHeroService.findAll();

        Assertions.assertEquals(2, superHeroListResponse.size());
        Assertions.assertEquals(superHeroList.get(0).getName(), superHeroListResponse.get(0).getName());
        Assertions.assertEquals(superHeroList.get(0).getSuperHeroName(), superHeroListResponse.get(0).getSuperHeroName());
        Assertions.assertEquals(superHeroList.get(0).getDescription(), superHeroListResponse.get(0).getDescription());
        Assertions.assertEquals(superHeroList.get(0).getComic(), superHeroListResponse.get(0).getComic());


    }
}
