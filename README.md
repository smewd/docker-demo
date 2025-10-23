## Beskrivning

Demo med webbapplikation som kan visa värden från application.properties eller miljövariabler.


### Properties-filer

Koden innehåller `application.properties`-filer. En default utan profil, en för profilen dev och en för profilen prod. 

```bash
src/main/resources/application.properties
src/main/resources/application-dev.properties
src/main/resources/application-prod.properties
```

### Miljövariabler

Default-filen `application.properties` innehåller två properties som populeras via Java- eller miljövariabler. Dessa motsvarar OD-variabler i verklig hostingmiljö. Värden omgivna av "${" och "}" kan ersättas med externa värden vid körning.

```bash
- string.value=${STR_VALUE}
- jdbc.url=${OD_JDBC_URL}
```

### Profil-specifika properties-filer

De profilspecifika filerna innehåller överskrivning av properties som definieras i default-filen.


## Bygga och köra

### Bygg och kör applikationen

```bash
mvn clean package

java -jar target/demo.war
```


### Podman

#### Dra ner images

I Powershell, kör kommandot `wsl -d podman-machine-default` för att komma in i Podman-subsystemet. Sätt ingen HTTP_PROXY.

Inne i subsystemet kan man sedan köra `podman pull tomcat:latest` eller liknande.

### Bygg och kör

```bash
podman build -t javaforum .

podman run -p 8080:8080 --env-file=<env-fil> javaforum
```


## Testning

Se TESTNING.md för detaljerade instruktioner för körning och verifiering.
