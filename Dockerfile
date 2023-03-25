FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests -Dmaven.compiler.target=11

FROM openjdk:11-jdk-slim
COPY --from=build /target/rent-a-car-2-classes.jar demo.jar
# ENV PORT=8080
EXPOSE 8080
ENTRYPOINT ["java","-jar","demo.jar"]