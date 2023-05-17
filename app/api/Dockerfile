# solution using JAR that will be built from code by the user (deprecated)
#FROM eclipse-temurin:17-jdk-jammy as builder
#RUN apt-get update
#WORKDIR /opt/app
#COPY .mvn/ .mvn
#COPY mvnw pom.xml ./
#RUN ./mvnw dependency:go-offline
#COPY ./src ./src
#RUN ./mvnw clean install
#
#FROM eclipse-temurin:17-jre-jammy
#RUN addgroup demogroup; adduser  --ingroup demogroup --disabled-password demo
#ARG PROFILE
#ENV ENV_PROFILE=${PROFILE}
#USER demo
#WORKDIR /opt/app
#EXPOSE 8888
#COPY --from=builder /opt/app/target/*.jar /opt/app/*.jar
#ENTRYPOINT java -jar -Dspring.profiles.active=$ENV_PROFILE "/opt/app/*.jar"

# solution using stable existing JAR
FROM eclipse-temurin:17-jre-jammy
ARG PROFILE
ENV ENV_PROFILE=${PROFILE}
EXPOSE 8888
ADD stable/sec-org-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT java -jar -Dspring.profiles.active=${ENV_PROFILE} app.jar
