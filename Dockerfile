FROM openjdk:11-jre-slim
MAINTAINER "Docker App MArcos"
WORKDIR /app

COPY ./target/*.jar ./app.jar
ENTRYPOINT ["java", "-jar", "/app/app.jar"]

EXPOSE 8080