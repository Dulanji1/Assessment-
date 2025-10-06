FROM maven:3.8.7-eclipse-temurin-17 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /app/target/todo-backend.jar ./todo-backend.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app/todo-backend.jar"]
