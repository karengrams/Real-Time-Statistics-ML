FROM maven:3.6.2-jdk-8 AS MAVEN_BUILD
COPY pom.xml /build/
COPY src /build/src/
WORKDIR /build/
RUN mvn package

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/target/Real-Time-Statistics-ML-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "Real-Time-Statistics-ML-0.0.1-SNAPSHOT.jar"]