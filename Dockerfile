FROM openjdk:11-jdk-slim
VOLUME /tmp
COPY target/*.jar productivitypal.jar
ENTRYPOINT ["java","-jar","/productivitypal.jar"]
EXPOSE 8080