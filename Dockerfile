#BUILD
FROM openjdk:21-slim AS builder

ENV ENV=production
WORKDIR /opt/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean install

#RUN
FROM openjdk:21-slim as final

WORKDIR /opt/app
ARG JAR_FILE=/opt/app/target/xprice-*SNAPSHOT.jar
COPY --from=builder ${JAR_FILE} xprice-app.jar

ENTRYPOINT ["java", "-jar", "xprice-app.jar"]
EXPOSE 8080