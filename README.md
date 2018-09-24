# sboot_micro_example
Spring Boot example repository for AWS Fargate microservice deployment. 
Designed for REST APIs and includes Swagger2 for documentation.
This repository serves a simple pre-configured template with common 
examples. Goal is to build services quickly, not fuck with spring.
Additional examples will be added in this spirit over time. 

pom.xml supports deployment to remote repository (artifactory) or a docker container repository.
`<docker.image.prefix>REPO-URI</docker.image.prefix>
<docker.image.name>REPO-NAME</docker.image.name>
<artifactory.url>URL</artifactory.url>`

To build:
- mvn clean
- mvn package
- mvn deploy (optional - artifactory)
- mvn dockerfile:build (optional - docker)
- mvn dockerfile:push (optional - docker)

To run:

java -jar "filename" or docker run "image"

AWS Fargate requires docker containers stored in ECS. 
To deploy to Fargate, simply configure pom.xml with corresponding ECS repository information.
Then use dockerfile:build and dockerfile:push to sync.

Repository includes optional configuration for threading and disabling spring's path extension 
auto-content resolver. 
- spring/MvcConfig.java
- spring/Thread.java

Note: It is recommended to remove these files if you are not going to use these features. 

Examples coming shortly:
- redis with lettuce
- redshift via JDBI