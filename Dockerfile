FROM maven:3.6.3-jdk-11

COPY pom.xml /app/
COPY src /app/src
WORKDIR /app

RUN mvn package