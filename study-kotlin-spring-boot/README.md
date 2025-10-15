# study-kotlin-spring-boot

A Kotlin Spring Boot application for study and experimentation.

## Project Overview
This project demonstrates a typical Kotlin Spring Boot setup with Maven, database integration, and Docker support. It includes code formatting via ktlint and is ready for local or containerized development.

## How to Run Locally

1. **Build and run with Maven:**
   ```sh
   mvn clean package
   java -jar target/study-kotlin-spring-boot-0.0.1-SNAPSHOT.jar
   ```
   Or, for development with hot reload:
   ```sh
   mvn spring-boot:run
   ```

2. **Set environment variables as needed** (see below for details).

## How to Run with Docker

1. **Build the JAR locally:**
   ```sh
   mvn clean package
   ```
2. **Build the Docker image:**
   ```sh
   docker build -t study-kotlin-spring-boot .
   ```
3. **Run the container:**
   ```sh
   docker run -p 8080:8080 \
     -e SPRING_PROFILES_ACTIVE=dev-docker \
     -e JAVA_OPTS="-Xms256m -Xmx512m" \
     study-kotlin-spring-boot
   ```

## Docker Configuration Details
- The Dockerfile uses a multi-stage build for smaller images and better caching.
- The JAR is built outside Docker and then copied into a minimal runtime image.
- The container exposes port 8080 by default.
- Environment variables can be passed at runtime to configure the application.

## Environment Variables

| Variable                | Default Value      | Where Used                        | Description |
|-------------------------|-------------------|-----------------------------------|-------------|
| `SPRING_PROFILES_ACTIVE`| `dev`             | Spring Boot profile selection     | Selects which `application-<profile>.yml` to use. E.g., `dev`, `dev-docker`, `prd` |
| `JAVA_OPTS`             | `-Xms256m -Xmx512m`| Docker CMD/ENTRYPOINT             | JVM options for memory and tuning |
| `DB_USER`               | `root`            | `application-*.yml` (datasource)  | Database username |
| `DB_PASS`               | `password`        | `application-*.yml` (datasource)  | Database password |
| `DB_URL`                | see yml           | `application-*.yml` (datasource)  | JDBC URL for the database |

- You can override these variables when running Docker or via your shell before running Maven/Java.
- The `SPRING_PROFILES_ACTIVE` variable determines which `application-<profile>.yml` file is loaded for configuration.

## Code Formatting

To automatically format your Kotlin code using ktlint, run:

```
mvn ktlint:format
```