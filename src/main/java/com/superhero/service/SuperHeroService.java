package com.superhero.service;

import com.superhero.controller.SuperHeroRequest;
import com.superhero.model.SuperHero;
import com.superhero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SuperHeroService implements SuperHeroServiceInterface {

    @Autowired
    private SuperHeroRepository superHeroRepository;


    public List<SuperHero> findAll() {
        return superHeroRepository.findAll();
    }

    public SuperHero findById(Long id) {
        Optional<SuperHero> superHeroOptional =  superHeroRepository.findById(id);
        return superHeroOptional.orElseGet(SuperHero::new);
    }

    public SuperHero update(Long id, SuperHeroRequest superHeroRequest) {
        Optional<SuperHero> superHeroOptional =  superHeroRepository.findById(id);
        SuperHero superHeroDatabase = null;
        if(superHeroOptional.isPresent()) {
            superHeroDatabase = superHeroOptional.get();
            if(superHeroRequest.getName() != null) superHeroDatabase.setName(superHeroRequest.getName());
            if(superHeroRequest.getSuperHeroName() != null) superHeroDatabase.setSuperHeroName(superHeroRequest.getSuperHeroName());
            if(superHeroRequest.getDescription() != null) superHeroDatabase.setDescription(superHeroRequest.getDescription());
            if(superHeroRequest.getComic() != null) superHeroDatabase.setComic(superHeroRequest.getComic());
        }
        return superHeroRepository.save(Objects.requireNonNull(superHeroDatabase));
    }

    public void delete(Long id) {
        SuperHero superHeroRetrieved = superHeroRepository.getById(id);
        superHeroRepository.delete(superHeroRetrieved);
    }

    @Override
    public List<SuperHero> findBySuperHeroNameBy(String query) {
        return superHeroRepository.findBySuperHeroNameContaining(query);
    }
}
