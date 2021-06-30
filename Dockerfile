FROM openjdk:11 as build

WORKDIR /home/shoplaptop

COPY .mvn .mvn
COPY mvnw .
COPY mvnw.cmd .
COPY pom.xml .

RUN chmod +x ./mvnw

RUN ./mvnw dependency:go-offline -B

COPY . .

RUN ./mvnw clean package spring-boot:repackage

FROM openjdk:11-jre as production

ARG HOME=/home/shoplaptop

WORKDIR /home/shoplaptop

COPY --from=build ${HOME}/src/main/resources src/main/resources

COPY --from=build ${HOME}/target target

RUN ls -la ./target
RUN ["chmod", "-R", "777", "/home/shoplaptop/target"]
RUN ls -la ./target

ENTRYPOINT ["java", "-jar", "target/ShopLaptop-0.0.1-SNAPSHOT.jar"]
#ENTRYPOINT /bin/bash echo Hello