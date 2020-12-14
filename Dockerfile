FROM openjdk:8-jdk-alpine
EXPOSE 8080
ARG JAR_FILE=target/enigma-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
