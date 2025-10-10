
# Starta från en officiell Tomcat-bild
FROM tomcat:10.1-jdk17-temurin

# Ta bort default webapps om du vill ha en ren instans
RUN rm -rf /usr/local/tomcat/webapps/*

# Kopiera din WAR-fil till Tomcats webapps-katalog
COPY target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war

# Exponera porten som Tomcat kör på
EXPOSE 8080

# Starta Tomcat
CMD ["catalina.sh", "run"]
