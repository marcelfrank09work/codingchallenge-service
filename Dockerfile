FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} service-1.0.0-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","target/service-1.0.0-SNAPSHOT.jar"]