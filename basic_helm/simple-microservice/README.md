# Introduction
 simple-microservice application is a simple spring boot based application generated from spring initializer. This example project will be used to demonstrate various openshift features and deployment methods.

## Features

simple-microservice does the follow:

- exposes rest endpoint /appname that prints the value `${app.name}` configured 
- App tries to fetch the property from Spring Boot Config server defined `spring.config.import` parameter
- if `${app.name}` is not defined, constant `myapp` will be returned
- 

## Tech

simple-microservice required:

- Java 11
- Spring Boot
- Spring Cloud config client

## Installation

simple-microservice requires Java 11 and Maven

Compile and pack the code

```sh
cd simple-microservice
mvn  -DskipTests clean package
mvn spring-boot:run
```

OR

```sh
mvn  -DskipTests clean package
cp target/*.jar dist/app.jar
java -jar dist/app.jar
```



Verify the deployment by navigating to your server address in
your preferred browser.

```
$ curl localhost:8100/appname
myapp$
```

## License
MIT