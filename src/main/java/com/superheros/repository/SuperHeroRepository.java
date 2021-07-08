package com.superheros.repository;

import com.superheros.model.SuperHero;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SuperHeroRepository extends JpaRepository<SuperHero, Long> {
}
