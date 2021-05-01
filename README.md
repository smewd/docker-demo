Demo med webbapplikation som deployas i olika Docker-miljöer

Med H2-databas som startas automatiskt i Tomcat

% mvn clean install

% docker build -t docker_demo .

% docker run -p 8080:8080 -it --rm docker_demo

% curl http://localhost:8080/docker-demo/hello


## Loggning
### Java-applikation
Sätt vilken katalog som Logback ska använda i Java-applikationen:
* pom.xml -> properties: logback-logs=...

Använd katalog-property:
  * pom.xml -> build -> resources -> resource -> filtering = true
  * src/main/resources -> logback.xml

### Tomcat
Publicera loggkatalog som virtuell katalog
* .env.passwords: LOGBACK_LOGS=...
    Denna skriver över Maven-propertyn
* dockerfiles/setenv.sh -> -Dlogback_logs=${LOGBACK_LOGS}
* dockerfiles/server.xml -> lägg till Context: <Context path="/logback-logs" docBase="${logback_logs}"/>
* dockerfiles/web.xml -> aktivera default-servlet och listings=true
