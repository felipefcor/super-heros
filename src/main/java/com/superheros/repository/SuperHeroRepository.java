package com.superheros.repository;

import com.superheros.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
    List<SuperHero> findBySuperHeroNameContaining(String query);
}
