FROM openjdk:17-jdk-slim

LABEL maintainer="Ngor SECK ngorsecka@gmail.com"

ADD /home/runner/work/thymeleaf-springboot/thymeleaf-springboot/staging/thymeleaf-springboot.jar thymeleaf-springboot.jar

ENTRYPOINT ["java", "-jar", "thymeleaf-springboot.jar"]

EXPOSE 8080