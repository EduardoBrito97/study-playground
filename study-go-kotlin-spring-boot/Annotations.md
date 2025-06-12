Although it is possible to use ```data class``` in Kotlin to define entities, it is not recommended to do so. Check the reasons on https://martinsjavacode.medium.com/por-que-n%C3%A3o-usar-data-class-como-entidades-jpa-e-evitar-dor-de-cabe%C3%A7a-9123dd4f9486.

In order for Spring Boot to be able to inject different beans, services or repositories, it must be able to "visualize it". To do so, its necessary for the package to be children of the package containing the @SpringBootApplication annotation.

Kotlin and spring automatically manipulates the responses into translating it to Json. If an ```User(id = 1, name = "TestUser")``` is returned by a ```@{}Mapping``` method, it'd return:
```
{
  "name": "TestUser",
  "id": 1
}
```

Spring deals with JSON bodies by adding the ```@RequestBody``` before the param in the function. To get the param from the path, ```@PathVariable``` is to be used.

To initialize the project, you can use https://start.spring.io/ and select the dependencies you want. We want to have it:

- Spring Web
  - Provides the web framework.
- Spring Boot Dev Tools
  - Allows fast application restarts and LiveReload.
- Lombok
  - Helps to reduce Boilerplate code. Kotlin isn't much benefited by it, but it helps with Java.
- Spring JPA
  - Helps managing database connection.
- Validation
  - Includes the spring-boot-starter-validation dependency that helps with API validation.
  - For javax.validation on kotlin, we need to prepend the annotations with ```field:```, since it expects to be added to the getter methods, and Kotlin abstracts it
- PostgreSQL Driver
  - Allows us to connect to PostgreSQL databases. Actually, you should look up for the driver you need on your own database. In my case, I was using PostgreSQL.
- Flyway Migration
  - Known framework for migrations.

Another useful information:
- If you're using H2 database, you can set up a ```data.sql``` file that it will run everytime the system starts;
- Spring supports both ```application.properties``` and ```application.yml```. Yaml files are more readable, thus more used.
- ```springdoc-openapi-starter-webmvc-ui``` does not support returning functions -- so make sure all your endpoints are not ```= {}``` instead of just ```{}```

---
**NOTES**

When using Intellij, you can use the JPABuddy tool to autogenerate migrations for flyway.

Although it's not available on Spring Initializr, you can add the ```springdoc-openapi-starter-webmvc-ui``` dependency to your project to have a Swagger UI for your API. It will automatically generate the documentation for your API based on the annotations you use in your code.

---