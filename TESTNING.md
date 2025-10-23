
## Som Spring Boot-applikation
Med överskrivning av properties samt miljövariabler och Spring-profil som parameter.

### Bygg:
```bash
mvn clean package
```

### Verifiering, generellt
Vid körning, gå till http://localhost:8080/data i en webbläsare, alternativt gör en request i Bruno eller liknande verktyg.

### Kör med default-värden utan profil satt.

#### Kommando
```bash
java -jar target/demo.war
```

#### Verifiering
- stringValue-fältet visar ett default-värde
- jdbcUrl-fältet visar ett default-värde
- Databas-fältet visar "Ingen databastyp satt"
- environmentName-fältet visar "Ingen profil satt"

### Kör med default-värden, dev-profil.

#### Kommando
```bash
java -Dspring.profiles.active=dev -jar target/demo.war
```

#### Verifiering
- jdbcUrl-fältet visar ett default-värde
- stringValue-fältet visar ett default-värde
- Databas-fältet visar "SQLite"
- environmentName-fältet visar "Utveckling"

### Kör med default-värden, prod-profil.

#### Kommando
```bash
java -Dspring.profiles.active=prod -jar target/demo.war
```

#### Verifiering
- jdbcUrl-fältet visar ett default-värde
- stringValue-fältet visar ett default-värde
- Databas-fältet visar "SQLite"
- environmentName-fältet visar "Produktion"

### Kör med överskrivning av Spring-properties, dev-profil.

#### Kommando
```bash
env jdbc.url='jdbc:sqlite:db_test.db' string.value='Hejsan hoppsan fran kommandoprompten' java -Dspring.profiles.active=dev -jar target/demo.war
```

#### Verifiering
- stringValue-fältet visar "Hejsan hoppsan fran kommandoprompten"
- jdbcUrl-fältet visar jdbc:sqlite:db_test.db
- Databas-fältet visar "SQLite"
- environmentName-fältet visar "Utveckling"

### Kör med miljövariabler, dev-profil.

#### Kommando
```bash
env OD_JDBC_URL='jdbc:sqlite:db_test.db' STR_VALUE='Hejsan hoppsan fran kommandoprompten' java -Dspring.profiles.active=dev -jar target/demo.war
```

#### Verifiering
- stringValue-fältet visar "Hejsan hoppsan fran kommandoprompten"
- jdbcUrl-fältet visar jdbc:sqlite:db_test.db
- Databas-fältet visar "SQLite"
- environmentName-fältet visar "Utveckling"

### Kör med miljövariabler, prod-profil.

#### Kommando
```bash
env OD_JDBC_URL='jdbc:sqlserver://dbd074' STR_VALUE='Hej hej fran CLI' java -Dspring.profiles.active=prod -jar target/demo.war
```

#### Verifiering
- stringValue-fältet visar "Hej hej fran CLI"
- jdbcUrl-fältet visar jdbc:sqlserver://dbd074
- Databas-fältet visar "MSSQL"
- environmentName-fältet visar "Produktion"



## I Tomcat, via Podman

### Verifiering, generellt
Vid körning, gå till http://localhost:8080/data i en webbläsare, alternativt gör en request i Bruno eller liknande verktyg.

### Utan profil, med defaultvärden

#### Kommando
```bash
podman run -p 8080:8080 -it javaforum
```

#### Verifiering
- stringValue-fältet visar ett default-värde
- jdbcUrl-fältet visar ett default-värde
- Databas-fältet visar "Ingen databastyp satt"
- environmentName-fältet visar "Ingen profil satt"

### Med dev-profil och miljövariabler

#### Kommando
```bash
podman run -p 8080:8080 --env-file=env.dev -it javaforum
```

#### Verifiering
- stringValue-fältet visar "Kör bara kör"
- jdbcUrl-fältet visar jdbc:hsqldb:mem:testdatabas
- databas-fältet visar "SQLite"
- environmentName-fältet visar "Utveckling"

### Med prod-profil och miljövariabler

#### Kommando
```bash
podman run -p 8080:8080 --env-file=env.prod -it javaforum
```

#### Verifiering
- stringValue-fältet visar "Hej hej fran CLI"
- jdbcUrl-fältet visar jdbc:sqlserver:dbd074/proddatabas
- databas-fältet visar "MSSQL"
- environmentName-fältet visar "Produktion"



## I Tomcat, via Docker

Ersätt `podman` med `docker` i föregående avsnitt. I övrigt är allt likadant.