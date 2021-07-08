package com.superhero.service;

import com.superhero.controller.SuperHeroRequest;
import com.superhero.model.SuperHero;

import java.util.List;

public interface SuperHeroServiceInterface {
    List<SuperHero> findAll();
    SuperHero findById(Long id);
    SuperHero update(Long id, SuperHeroRequest superHeroRequest);
    void delete(Long id);
    List<SuperHero> findBySuperHeroNameBy(String query);
}
