FROM tomcat:9.0.27-jdk8-adoptopenjdk-openj9

RUN rm -rf ${CATALINA_HOME}/webapps/*
COPY target/docker-demo-1.0-SNAPSHOT.war ${CATALINA_HOME}/webapps/docker-demo.war

COPY dockerfiles/setenv.sh ${CATALINA_HOME}/bin
COPY dockerfiles/context.xml ${CATALINA_HOME}/conf
COPY dockerfiles/server.xml ${CATALINA_HOME}/conf

EXPOSE 8080
