## Fetches the already existing image for JDK 21
#FROM maven:3.9.9-eclipse-temurin-21
#
## Exposes port 8080
#EXPOSE 8080
#
## Creating the JAR object
#WORKDIR /app
#COPY pom.xml .
#
#RUN mvn clean package -DskipTests
#COPY . .
#
## Moving the JAR object to the image
##ADD /target/study-kotlin-spring-boot*.jar forum.jar
#
## Execute the JAR object
#ENTRYPOINT exec "mvn spring-boot:run -Dspring-boot.run.profiles=${SPRING_ACTIVE_PROFILE} -Dspring-boot.run.jvmArguments='${JAVA_OPTS}'"

# Fetches the already existing image for JDK 21
FROM maven:3.9.9-eclipse-temurin-21

# Exposes port 8080
EXPOSE 8080


# Creating the JAR object
WORKDIR /app

COPY pom.xml .

# Trying to use cache for maven
#RUN mvn dependency:go-offline

#COPY . .

#COPY src ./src

#RUN mvn clean package -DskipTests

# Moving the JAR object to the image
ADD /target/study-kotlin-spring-boot*.jar forum.jar

# Execute the JAR object
ENTRYPOINT ["java", "-jar", "forum.jar", "--spring.profiles.active=dev-docker", "-Dspring-boot.run.jvmArguments='${JAVA_OPTS}'"]