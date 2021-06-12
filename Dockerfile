FROM openjdk:11 as build

WORKDIR /home/shoplaptop

COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline

COPY . .

ENTRYPOINT ./mvnw spring-boot:run