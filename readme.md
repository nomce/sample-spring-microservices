# Microservices with Spring Cloud Demo Project

The original project and its readme can be found: https://github.com/piomin/sample-spring-microservices

# How to run

- to build the services: ``mvn clean install``
- to run the tests: ``mvn clean compiler:testCompile surefire:test``
- to build and run the docker images: ``docker-compose up --build -d``

To run the whole execution with Jenkins, either copy or import
the ``JenkinsFile`` to a Jenkins Pipeline and run the project. 
If you run it by importing the file, remove the "Checkout code" stage, it is redundant.

# Jenkins CI build

The build is separated in multiple stages:

1. Code Checkout: clone the repository
2. Build: build the Spring Services
3. Create Images: create Docker images based on Dockerfiles
4. Run Containers: Deploy the images to containers and run them 
5. Tests: run the tests
6. Final: get the JUnit report and create an artefact

In the first four stages, if there is an error, the execution will be marked as Unstable.

Every container has a health check for itself. The "customer-service" docker image has an additional
readiness (is the container up) and liveness (is the container running) check for "account-service", 
in order to be sure that before this container is started, all of the dependent contaniners are up and running.