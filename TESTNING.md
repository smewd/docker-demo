
## Spring Boot-applikation
Med miljövariabel och Spring-profil som parameter

```bash
mvn clean package
STRING_VALUE='Hejsan hoppsan fran kommandoprompten' java -Dspring.profiles.active=dev -jar target/demo.war
```

## I Tomcat, via Docker

```bash
mvn clean package
docker build -t props-demo .
docker run -p 8080:8080 --env-file=env.dev props-demo
docker run -p 8080:8080 --env-file=env.prod props-demo
```


## I Tomcat, via Docker

```bash
mvn clean package
podman build -t props-demo .
podman run -p 8080:8080 --env-file=env.dev props-demo
podman run -p 8080:8080 --env-file=env.prod props-demo
```
