# syntax=docker/dockerfile:1

FROM openjdk:17
ADD target/restrain-app-scan-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
