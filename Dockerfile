FROM open-liberty:20.0.0.9-kernel-java8-openj9

COPY target/docker-demo-1.0-SNAPSHOT.war /config/dropins/docker-demo.war
COPY dockerfiles/server.xml /config/

EXPOSE 9080
