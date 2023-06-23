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


# DynamoDB

This project uses the enhanced extension for dynamodb. 

Configured with `quarkus:add-extension -Dextensions=amazon-dynamodb-enhanced -f pom.xml`

https://quarkiverse.github.io/quarkiverse-docs/quarkus-amazon-services/dev/amazon-dynamodb.html

Uses localstack (https://hub.docker.com/r/localstack/localstack) to create a local "cloud" environment
in order to run DynamoDB.

Doesn't look like quarkus dev mode starts localstack. 
At the moment I'm trying to run it externally. So start localstack with the localstack cli

Quarkus dev mode seems to start both test containers and localstack.
However, I'm not sure localstack continues to run

When I use the below to start local stack manually and apply the table updates I can't get quarkus to see the table.
Maybe differnet instances of localstack?

aws --endpoint-url=http://localhost:4566 cloudformation deploy --template-file sam/dynamodb.yaml --stack-name topic-BE-dynamodb --capabilities CAPABILITY_IAM
aws cloudformation delete-stack --stack-name topic-BE-dynamodb




## Some local stack commands for reference.

`localstack start`
See what services are available with 

`localstack status services`

Or
`http://localhost:4566/health`


DynamoDB seems to be available, but not started...

