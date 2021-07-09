package com.superhero.service;

import com.superhero.controller.SuperHeroRequest;
import com.superhero.exception.SuperHeroNotFound;
import com.superhero.model.SuperHero;
import com.superhero.repository.SuperHeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SuperHeroService implements SuperHeroServiceInterface {

    private static final String SUPER_HERO_NOT_FOUND = "Super Hero not found";

    @Autowired
    private SuperHeroRepository superHeroRepository;


    @Override
    public List<SuperHero> findAll() {
        return superHeroRepository.findAll();
    }

    @Override
    public SuperHero findById(Long id) {
        Optional<SuperHero> superHeroOptional =  superHeroRepository.findById(id);
        if(superHeroOptional.isEmpty()) {
            throw new SuperHeroNotFound(SUPER_HERO_NOT_FOUND);
        } else {
            return superHeroOptional.get();
        }
    }

    @Override
    public SuperHero update(Long id, SuperHeroRequest superHeroRequest) {
        Optional<SuperHero> superHeroOptional =  superHeroRepository.findById(id);
        if(superHeroOptional.isEmpty()) {
            throw new SuperHeroNotFound(SUPER_HERO_NOT_FOUND);
        } else {
            SuperHero superHeroDatabase = superHeroOptional.get();
            if(superHeroRequest.getName() != null) superHeroDatabase.setName(superHeroRequest.getName());
            if(superHeroRequest.getSuperHeroName() != null) superHeroDatabase.setSuperHeroName(superHeroRequest.getSuperHeroName());
            if(superHeroRequest.getDescription() != null) superHeroDatabase.setDescription(superHeroRequest.getDescription());
            if(superHeroRequest.getComic() != null) superHeroDatabase.setComic(superHeroRequest.getComic());

            return superHeroRepository.save(Objects.requireNonNull(superHeroDatabase));
        }
    }

    @Override
    public void delete(Long id) {
        Optional<SuperHero> superHeroOptional =  superHeroRepository.findById(id);
        if(superHeroOptional.isEmpty()) {
            throw new SuperHeroNotFound(SUPER_HERO_NOT_FOUND);
        } else {
            superHeroRepository.delete(superHeroOptional.get());
        }
    }

    @Override
    public List<SuperHero> findBySuperHeroNameBy(String query) {
        return superHeroRepository.findBySuperHeroNameContaining(query);
    }
}
