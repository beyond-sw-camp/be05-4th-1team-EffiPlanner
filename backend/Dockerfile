# FROM openjdk:17

# ARG JAR_FILE=build/libs/*.jar
# COPY ${JAR_FILE} backend.jar

# ENTRYPOINT ["java", "-jar", "/backend.jar"]

# git action
FROM openjdk:17

WORKDIR /add-backend

ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} 1teambackend.jar

ENTRYPOINT ["java", "-jar", "/1teambackend.jar"]