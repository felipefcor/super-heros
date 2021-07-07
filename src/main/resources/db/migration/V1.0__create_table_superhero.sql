CREATE TABLE superHero (
    id SERIAL NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    super_hero_name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL,
    comic VARCHAR(50)
);