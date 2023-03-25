FROM maven:3.8.2-jdk-11 AS build
COPY . .
RUN mvn clean package -DskipTests -Dmaven.compiler.target=11

FROM tomcat:9-jdk11
COPY --from=build /target/rent-a-car-2.war $CATALINA_HOME/webapps/demo.war
EXPOSE 8080
ENTRYPOINT ["catalina.sh", "run"]
#FROM openjdk:11-jdk-slim

#COPY --from=build /target/rent-a-car-2.war demo.war
#EXPOSE 8080
#ENTRYPOINT ["mvn", "tomcat7:run"]