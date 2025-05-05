# syntax=docker/dockerfile:1

# === Build stage ===
FROM gradle:8.11.1-jdk21 AS build
WORKDIR /app
COPY . .
RUN ./gradlew build

# === Run stage ===
FROM openjdk:21-jdk
WORKDIR /app
COPY --from=build /app/build/libs/todo-api.jar todo-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "todo-api.jar"]
