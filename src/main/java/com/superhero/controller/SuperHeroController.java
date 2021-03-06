package com.superhero.controller;

import com.superhero.model.SuperHero;
import com.superhero.service.SuperHeroServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/superhero")
public class SuperHeroController {

    @Autowired
    SuperHeroServiceInterface superHeroServiceInterface;

    @GetMapping
    public ResponseEntity<List<SuperHero>> findAll() {
        List<SuperHero> listSuperHero = superHeroServiceInterface.findAll();
        return new ResponseEntity<>(listSuperHero, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<SuperHero> findById(@PathVariable Long id) {
        SuperHero superHero = superHeroServiceInterface.findById(id);
        return new ResponseEntity<>(superHero, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SuperHero> update(@PathVariable Long id, @Valid @RequestBody SuperHeroRequest superHeroRequest) {
        SuperHero superHeroUpdated = superHeroServiceInterface.update(id, superHeroRequest);
        return new ResponseEntity<>(superHeroUpdated, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        superHeroServiceInterface.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/findBySuperHeroNameBy/{query}")
    public ResponseEntity<List<SuperHero>> findBySuperHeroNameBy(@PathVariable String query) {
        List<SuperHero> superHeroList = superHeroServiceInterface.findBySuperHeroName(query);
        return new ResponseEntity<>(superHeroList, HttpStatus.OK);
    }
}
