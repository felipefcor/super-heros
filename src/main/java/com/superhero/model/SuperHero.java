package com.superhero.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="super_hero")
public class SuperHero implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "super_hero_name")
    private String superHeroName;
    @Column(name = "description")
    private String description;
    @Column(name = "comic")
    private String comic;

    public SuperHero(String name, String superHeroName, String description, String comic) {
        this.name = name;
        this.superHeroName = superHeroName;
        this.description = description;
        this.comic = comic;
    }

    public SuperHero() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperHeroName() {
        return superHeroName;
    }

    public void setSuperHeroName(String superHeroName) {
        this.superHeroName = superHeroName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComic() {
        return comic;
    }

    public void setComic(String comic) {
        this.comic = comic;
    }
}
