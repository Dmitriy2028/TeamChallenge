FROM gradle:latest AS build
WORKDIR /home/gradle/src
COPY --chown=gradle:gradle . /home/gradle/src
RUN gradle build --no-daemon -x test

FROM openjdk:17-jdk-slim

EXPOSE 8080

WORKDIR /app

COPY --from=build /home/gradle/src/build/libs/*.jar /app/spring-boot-application.jar

ENTRYPOINT ["java", "-jar", "/app/spring-boot-application.jar"]
