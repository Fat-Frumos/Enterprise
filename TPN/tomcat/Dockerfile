FROM tomcat:8-jre8  

RUN echo "export JAVA_OPTS=\"-Dapp.env=staging\"" > /usr/local/tomcat/bin/setenv.sh  
COPY ./rent-a-car-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/app.war

CMD ["catalina.sh", "run"] 