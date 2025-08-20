# Fetches the already existing image for JDK 21
FROM maven:3.9.8-eclipse-temurin-21

# Exposes port 8080
EXPOSE 8080


# Creating the JAR object
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:resolve
COPY . .
RUN mvn clean package -DskipTests

# Moving the JAR object to the image
#ADD /target/study-kotlin-spring-boot*.jar forum.jar

# Execute the JAR object
ENTRYPOINT ["mvn", "spring-boot:run", "-Dspring-boot.run.profiles=$SPRING_ACTIVE_PROFILE", "-Dspring-boot.run.jvmArguments=$JAVA_OPTS"]