FROM tomcat:9.0.27-jdk8-adoptopenjdk-openj9

RUN rm -rf ${CATALINA_HOME}/webapps/*
#COPY target/docker-demo-1.0-SNAPSHOT.war ${CATALINA_HOME}/webapps/docker-demo.war

COPY dockerfiles/setenv.sh ${CATALINA_HOME}/bin
COPY dockerfiles/context.xml ${CATALINA_HOME}/conf
COPY dockerfiles/server.xml ${CATALINA_HOME}/conf
COPY dockerfiles/web.xml ${CATALINA_HOME}/conf

ADD dockerfiles/ ${CATALINA_HOME}/test/dockerfiles
ADD dockerfiles/ ${CATALINA_HOME}/test2/dockerfiles2

ENV JPDA_ADDRESS=8000
ENV JPDA_TRANSPORT=dt_socket

EXPOSE 8000 8080

CMD ["catalina.sh", "jpda", "run"]
