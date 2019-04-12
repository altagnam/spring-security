FROM openjdk:8-jdk-alpine
MAINTAINER Rafael Altagnam
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]