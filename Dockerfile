FROM openjdk:11
MAINTAINER DaniilK
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} bookkeeping-app.jar
ENTRYPOINT ["java", "-jar", "/bookkeeping-app.jar"]