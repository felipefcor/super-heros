package com.superheros.service;

import com.superheros.controller.SuperHeroRequest;
import com.superheros.model.SuperHero;

import java.util.List;

public interface SuperHeroServiceInterface {
    List<SuperHero> findAll();
    SuperHero findById(Long id);
    SuperHero update(Long id, SuperHeroRequest superHeroRequest);
    void delete(Long id);
}
