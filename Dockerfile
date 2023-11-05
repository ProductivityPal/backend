FROM eclipse-temurin:17-jdk-alpine
VOLUME /tmp
COPY target/*.jar productivitypal.jar
ENTRYPOINT ["java","-jar","/productivitypal.jar"]
EXPOSE 8080