FROM maven:3.8.4 AS BUILD

WORKDIR /app

COPY pom.xml .
COPY src src

RUN mvn clean package

FROM openjdk:21-slim-buster

WORKDIR /app

COPY --from=build /app/target/*jar app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]

