Demo med webbapplikation som kan visa värden från application.properties eller miljövariabler.

## Properties
Innehåll i filen `src/main/resources/application.properties`:

```
string.value=@STRING_VALUE@
```
Värden i `application.properties` omgivna av "@" kan ersättas med externa värden i runtime.


## Kompilera

```bash
mvn clean package
```


## Kör som applikation

```bash
java -jar target/demo.war
```

Gå till http://localhost:8080/stringvalue. Texten "@STRING_VALUE@" visas.


### Skicka in Spring-property

```bash
java -Dstring.value=abcde -jar target/demo.war
```

Java-parametern skriver över värdet i propertyn `string.value` i `application.properties`.

Gå till http://localhost:8080/stringvalue. Texten "abcde" visas.


### Använd miljövariabel

```bash
STRING_VALUE=12345 java -jar target/demo.war
```

Gå till http://localhost:8080/data. Verifiera att texten "12345" visas i fältet stringValue.


## Bygg med Docker
```bash
docker build -t demo .
```

## Kör med Docker
```bash
docker run -p 8080:8080 demo
```

Gå till http://localhost:8080/data. Verifiera att texten "@STRING_VALUE@" visas i fältet stringValue.


## Använd miljövariabel
```bash
docker run -p 8080:8080 -e STRING_VALUE='Kör bara kör!' demo
```

Gå till http://localhost:8080/data. Verifiera att texten "Kör bara kör!" visas i fältet stringValue.
