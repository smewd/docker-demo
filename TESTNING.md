
## Spring Boot-applikation
Med miljövariabel och Spring-profil som parameter

```bash
mvn clean package
OD_JDBC_URL='jdbc:h2:mem/db_test' STRING_VALUE='Hejsan hoppsan fran kommandoprompten' java -Dspring.profiles.active=dev -jar target/demo.war
OD_JDBC_URL='jdbc:mssql:dbd074/db_prod' STRING_VALUE='Hej hej fran CLI' java -Dspring.profiles.active=prod -jar target/demo.war
```

### Verifiering
Gå till http://localhost:8080/data. 

#### Spring-profil dev
* stringValue-fältet visar "Hejsan hoppsan fran kommandoprompten"
* Databas-fältet visar "HSQLDB"
* environmentName-fältet visar "Utveckling"

#### Spring-profil prod
* stringValue-fältet visar "Hej hej fran CLI"
* Databas-fältet visar "MSSQL"
* environmentName-fältet visar "Produktion"


## I Tomcat, via Docker

```bash
mvn clean package
docker build -t props-demo .
docker run -p 8080:8080 --env-file=env.dev props-demo
docker run -p 8080:8080 --env-file=env.prod props-demo
```

### Verifiering
Gå till http://localhost:8080/data. 

Verifiera att texten  visas i fältet stringValue.

#### env.dev
* stringValue-fältet visar "Kör bara kör"
* Databas-fältet visar "HSQLDB"
* environmentName-fältet visar "Utveckling"

#### env.prod
* stringValue-fältet visar "Prod bara prod"
* Databas-fältet visar "MSSQL"
* environmentName-fältet visar "Produktion"


## I Tomcat, via Podman

```bash
mvn clean package
podman build -t props-demo .
podman run -p 8080:8080 --env-file=env.dev props-demo
podman run -p 8080:8080 --env-file=env.prod props-demo
```

### Verifiering
Gå till http://localhost:8080/data. 

#### env.dev
* stringValue-fältet visar "Kör bara kör"
* Databas-fältet visar "HSQLDB"
* environmentName-fältet visar "Utveckling"

#### env.prod
* stringValue-fältet visar "Prod bara prod"
* Databas-fältet visar "MSSQL"
* environmentName-fältet visar "Produktion"



## Podman

# Dra ner images

I Powershell, kör kommandot `wsl -d podman-machine-default` för att komma in i Podman-subsystemet. Sätt ingen HTTP_PROXY.

Inne i subsystemet kan man sedan köra `podman pull alpine` eller liknande.


## Körschema

Visa koden. Application.properties-filer.
OD_JDBC_URL som värdebärare för env-variabler. Motsvarar OD-variabler i verklig miljö.

```bash
mvn clean package

java -Dspring.profiles.active=dev -jar target/demo.war

java -Dspring.profiles.active=prod -jar target/demo.war

OD_JDBC_URL='jdbc:h2:mem/db_test' STRING_VALUE='Hej Java-forum!' java -Dspring.profiles.active=dev -jar target/demo.war

OD_JDBC_URL='jdbc:mssql:db_prod' STRING_VALUE='Hej Java-forum!' java -Dspring.profiles.active=dev -jar target/demo.war

podman build -t javaforum .

podman run --env-file=env.dev -p 8080:8080 -t javaforum

podman run --env-file=env.prod -p 8080:8080 -t javaforum
```