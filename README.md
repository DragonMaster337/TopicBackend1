# TopicBackend

## Initial Project Setup
https://quarkus.io/guides/amazon-lambda-http
Install the SAM CLI and the AWS CLI
Configure the AWS CLI with
`aws configure`

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

### In quarkus dev mode
`mvn quarkus:dev`
Hit the endpoint with http://localhost:8080/hello

### Using SAM Local to emulate the AWS environment
`sam local start-api --template target/sam.jvm.yaml`
Hit the endpoint with http://127.0.0.1:3000/hello

## Deploy to AWS
`sam deploy -t target/sam.jvm.yaml -g`
