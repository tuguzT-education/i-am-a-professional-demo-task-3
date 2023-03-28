FROM gradle:8.0.2-jdk17-jammy AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle buildFatJar --no-daemon

FROM openjdk:19-jdk-alpine
EXPOSE 8080
ENV DATABASE_URI jdbc:h2:file:./build/db
ENV DATABASE_DRIVER org.h2.Driver
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
