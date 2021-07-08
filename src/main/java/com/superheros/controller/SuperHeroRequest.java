package com.superheros.controller;

import javax.validation.constraints.NotBlank;

public class SuperHeroRequest {
    @NotBlank(message = "name field does not appear in the request is improperly spelled or is empty.")
    private String name;
    @NotBlank(message = "superHeroName field does not appear in the request is improperly spelled or is empty.")
    private String superHeroName;
    @NotBlank(message = "description field does not appear in the request is improperly spelled or is empty.")
    private String description;
    @NotBlank(message = "comic field does not appear in the request is improperly spelled or is empty.")
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
