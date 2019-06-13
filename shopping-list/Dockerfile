FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar /app.jar
EXPOSE 8080
ADD src/main/resources/application.properties /config/application.properties
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom", "-Dspring.config.location=/config/application.properties", "-jar","/app.jar"]