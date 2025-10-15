
## Spring Boot-applikation
Med miljövariabel och Spring-profil som parameter

```bash
mvn clean package
STRING_VALUE='Hejsan hoppsan fran kommandoprompten' java -Dspring.profiles.active=dev -jar target/demo.war
```

## I Tomcat, via Docker/Podman

```bash
mvn clean package
docker build -t props-demo .
docker run -p 8080:8080 -e STRING_VALUE='Kör bara kör!' -e spring.profiles.active=dev props-demo
```
