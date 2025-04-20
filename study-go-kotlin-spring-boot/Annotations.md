Entities cannot be ````data class```` in kotlin -- it must use open vars instead.

In order for Spring Boot to be able to inject different beans, services or repositories, it must be able to "visualize it". To do so, its necessary for the package to be children of the package containing the @SpringBootApplication annotation.

Kotlin and spring automatically manipulates the responses into translating it to Json. If an ```User(id = 1, name = "TestUser")``` is returned by a ```@{}Mapping``` method, it'd return:
```
{
  "name": "TestUser",
  "id": 1
}
```