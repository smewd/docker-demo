Demo med webbapplikation som deployas i olika Docker-miljöer

Med H2-databas som startas automatiskt i Tomcat

% mvn clean install

% docker build -t docker_demo .

% docker run -p 8080:8080 -it --rm docker_demo

% curl http://localhost:8080/docker-demo/hello
