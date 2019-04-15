FROM openjdk:8-jdk-alpine
MAINTAINER Rafael Altagnam
USE root
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]