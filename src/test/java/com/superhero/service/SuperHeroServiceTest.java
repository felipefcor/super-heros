package com.superhero.service;

import com.superhero.controller.SuperHeroRequest;
import com.superhero.model.SuperHero;
import com.superhero.repository.SuperHeroRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
    void findAllWithResultsShouldReturnAListOfSuperHeroCorrectly(){
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
        String comic2 = "Marvel";
        SuperHero superHero2 = new SuperHero(name2, superHeroName2, description2, comic2);

        List<SuperHero> superHeroList = Stream.of(superHero1, superHero2).collect(Collectors.toList());

        Mockito.when(superHeroRepository.findAll()).thenReturn(superHeroList);

        List<SuperHero> superHeroListResponse = superHeroService.findAll();

        Assertions.assertEquals(2, superHeroListResponse.size());
        Assertions.assertEquals(superHeroList.get(0).getName(), superHeroListResponse.get(0).getName());
        Assertions.assertEquals(superHeroList.get(0).getSuperHeroName(), superHeroListResponse.get(0).getSuperHeroName());
        Assertions.assertEquals(superHeroList.get(0).getDescription(), superHeroListResponse.get(0).getDescription());
        Assertions.assertEquals(superHeroList.get(0).getComic(), superHeroListResponse.get(0).getComic());
        Assertions.assertEquals(superHeroList.get(1).getName(), superHeroListResponse.get(1).getName());
        Assertions.assertEquals(superHeroList.get(1).getSuperHeroName(), superHeroListResponse.get(1).getSuperHeroName());
        Assertions.assertEquals(superHeroList.get(1).getDescription(), superHeroListResponse.get(1).getDescription());
        Assertions.assertEquals(superHeroList.get(1).getComic(), superHeroListResponse.get(1).getComic());
    }

    @Test
    void findAllWithNoResultsShouldReturnAnEmptyListOfSuperHero(){
        Mockito.when(superHeroRepository.findAll()).thenReturn(new ArrayList<>());

        List<SuperHero> superHeroListResponse = superHeroService.findAll();

        Assertions.assertEquals(0, superHeroListResponse.size());
    }

    @Test
    void findByIdWithCorrectIdShouldReturnASuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);
        superHero.setId(1L);

        Mockito.when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superHero));

        SuperHero superHeroResponse = superHeroService.findById(1L);

        Assertions.assertEquals(superHero.getName(), superHeroResponse.getName());
        Assertions.assertEquals(superHero.getSuperHeroName(), superHeroResponse.getSuperHeroName());
        Assertions.assertEquals(superHero.getDescription(), superHeroResponse.getDescription());
        Assertions.assertEquals(superHero.getComic(), superHeroResponse.getComic());
    }

    @Test
    void updateWithCorrectIdAndCorrectSuperHeroRequestShouldModifyAndReturnASuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);
        superHero.setId(1L);

        String newSuperHeroName = "SPIDERMAN";
        SuperHeroRequest superHeroRequest = new SuperHeroRequest(name, newSuperHeroName, description, comic);

        Mockito.when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superHero));
        Mockito.when(superHeroRepository.save(Mockito.any())).thenReturn(superHero);

        SuperHero superHeroResponse = superHeroService.update(1L, superHeroRequest);

        Assertions.assertEquals(superHero.getName(), superHeroResponse.getName());
        Assertions.assertEquals(superHeroRequest.getSuperHeroName(), superHeroResponse.getSuperHeroName());
        Assertions.assertEquals(superHero.getDescription(), superHeroResponse.getDescription());
        Assertions.assertEquals(superHero.getComic(), superHeroResponse.getComic());
    }

    @Test
    void deleteWithCorrectIdCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);
        superHero.setId(1L);

        Mockito.when(superHeroRepository.findById(1L)).thenReturn(Optional.of(superHero));

        superHeroService.delete(1L);

        ArgumentCaptor<SuperHero> captorSuperHero = ArgumentCaptor.forClass(SuperHero.class);

        Mockito.verify(superHeroRepository, Mockito.times(1))
                .delete(captorSuperHero.capture());

        SuperHero superHeroCaptured = captorSuperHero.getValue();

        Assertions.assertEquals(superHero.getName(), superHeroCaptured.getName());
        Assertions.assertEquals(superHero.getSuperHeroName(), superHeroCaptured.getSuperHeroName());
        Assertions.assertEquals(superHero.getDescription(), superHeroCaptured.getDescription());
        Assertions.assertEquals(superHero.getComic(), superHeroCaptured.getComic());
    }

    @Test
    void findBySuperHeroNameByWithCorrectQueryShouldReturnAListOfMatchingSuperHeroCorrectly(){
        String name = "Peter Parker";
        String superHeroName = "Spider-man";
        String description = "Fuerza, rapidez y reflejos proporcionales a una araña. Capacidad de adherirse a casi cualquier superficie.";
        String comic = "Marvel";
        SuperHero superHero = new SuperHero(name, superHeroName, description, comic);

        String name2 = "Clark Kent";
        String superHeroName2 = "'Superman'";
        String description2 = "Fuerza sobrehumana, velocidad, resistencia, agilidad, reflejos, durabilidad, sentidos y longevidad. Poderes oculares. Agudeza sobrehumana. " +
                "Visión de calor. Visión del espectro electromagnético. Visión microscópica. Aliento sobrehumano. Invulnerabilidad. Volar";
        String comic2 = "Marvel";

        SuperHero superHero2 = new SuperHero(name2, superHeroName2, description2, comic2);

        List<SuperHero> superHeroList = Stream.of(superHero, superHero2).collect(Collectors.toList());

        Mockito.when(superHeroRepository.findBySuperHeroNameContaining("man")).thenReturn(superHeroList);

        List<SuperHero> superHeroListResponse = superHeroService.findBySuperHeroNameBy("man");

        Assertions.assertEquals(2, superHeroListResponse.size());
        Assertions.assertEquals(superHeroList.get(0).getName(), superHeroListResponse.get(0).getName());
        Assertions.assertEquals(superHeroList.get(0).getSuperHeroName(), superHeroListResponse.get(0).getSuperHeroName());
        Assertions.assertEquals(superHeroList.get(0).getDescription(), superHeroListResponse.get(0).getDescription());
        Assertions.assertEquals(superHeroList.get(0).getComic(), superHeroListResponse.get(0).getComic());
        Assertions.assertEquals(superHeroList.get(1).getName(), superHeroListResponse.get(1).getName());
        Assertions.assertEquals(superHeroList.get(1).getSuperHeroName(), superHeroListResponse.get(1).getSuperHeroName());
        Assertions.assertEquals(superHeroList.get(1).getDescription(), superHeroListResponse.get(1).getDescription());
        Assertions.assertEquals(superHeroList.get(1).getComic(), superHeroListResponse.get(1).getComic());
    }
}
