# superhero-app

[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

Api to manage Superheros made with [Spring Boot](http://projects.spring.io/spring-boot/).

## Requirements

For building and running the application you need:

- [JDK 1.11](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. 

* First one, you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

* With docker

```shell
docker build -t superhero .
docker run --name superhero -p 8080:8080 superhero
```


## Entry points

In order to access to the API you should authenticate. 
There are several roles with distinct permissions.

Access to the main API:

```shell
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config
```

Access to H2 Database
```shell
http://localhost:8080/h2-console
```


## Copyright

Released under the Apache License 2.0. See the [LICENSE](https://github.com/codecentric/springboot-sample-app/blob/master/LICENSE) file.