FROM tomcat:9.0.27-jdk8-adoptopenjdk-openj9

RUN rm -rf ${CATALINA_HOME}/webapps/*
COPY target/docker-demo-1.0-SNAPSHOT.war ${CATALINA_HOME}/webapps/ROOT.war
COPY dockerfiles/server.xml ${CATALINA_HOME}/conf

ENV CATALINA_OPTS="${CATALINA_OPTS} -Xms4096m -Xmx4096m"

EXPOSE 8080
