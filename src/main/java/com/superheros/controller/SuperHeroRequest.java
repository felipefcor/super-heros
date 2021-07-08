package com.superheros.controller;

public class SuperHeroRequest {
    private String name;
    private String superHeroName;
    private String description;
    private String comic;

    public SuperHeroRequest(String name, String superHeroName, String description, String comic) {
        this.name = name;
        this.superHeroName = superHeroName;
        this.description = description;
        this.comic = comic;
    }

    public String getName() {
        return name;
    }

    public String getSuperHeroName() {
        return superHeroName;
    }


    public String getDescription() {
        return description;
    }


    public String getComic() {
        return comic;
    }

}
