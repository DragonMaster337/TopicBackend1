# TopicBackend

## Initial Project Setup
https://quarkus.io/guides/amazon-lambda-http

## Project Basics

This project uses AWS API Gateway REST API's. To support this in quarkus, we use the ` extension.quarkus-amazon-lambda-rest`

It's using the JAXRS rest framework.

## Build Java deployable artifact
`./mvnw install`

to build using a docker container (required when not building on a Linux environment)
`./mvnw install -Dnative -DskipTests -Dquarkus.native.container-build=true`

This will generate 3 key files
* function.zip - lambda deployment file
* sam.jvm.yaml - sam cli deployment script 
* sam.native.yaml - sam cli deployment script for native

## Running locally
`mvn quarkus:dev`

Hit the endpoint with http://localhost:8080/hello
