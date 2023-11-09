 # Build stage
 #
 FROM maven:3.8.3-openjdk-17 AS build
 WORKDIR /app
 COPY . /app/
 RUN mvn clean package

 #
 # Package stage
 #
 FROM openjdk:17-alpine
COPY --from=build /target/productivitypal-0.0.1-SNAPSHOT.jar productivitypal.jar
 EXPOSE 8080
 ENTRYPOINT ["java","-jar","productivitypal.jar"]