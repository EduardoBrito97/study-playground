Entities cannot be ````data class```` in kotlin -- it must use open vars instead.

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