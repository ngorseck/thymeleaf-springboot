FROM openjdk:17-jdk-slim

LABEL maintainer="Ngor SECK ngorsecka@gmail.com"

EXPOSE 8080

RUN mkdir -p /app/data

ADD docker/thymeleaf-springboot.jar thymeleaf-springboot.jar

ENTRYPOINT ["java", "-jar", "thymeleaf-springboot.jar"]