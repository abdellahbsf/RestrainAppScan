# syntax=docker/dockerfile:1


#FROM openjdk:17
# Use an image that includes Maven for building
FROM maven:3.8.4 as build
WORKDIR /app
COPY . /app
RUN mvn clean package -DskipTests

# Use a lightweight image for the final application
FROM openjdk:17-slim
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
#ADD target/restrain-app-scan-0.0.1-SNAPSHOT.jar app.jar


