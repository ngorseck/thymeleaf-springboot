FROM openjdk:17-jdk-slim

LABEL maintainer="Ngor SECK ngorsecka@gmail.com"

COPY thymeleaf-springboot.jar thymeleaf-springboot.jar

ENTRYPOINT ["java", "-jar", "thymeleaf-springboot.jar"]

EXPOSE 8080