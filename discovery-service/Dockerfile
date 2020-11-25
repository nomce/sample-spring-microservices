FROM openjdk:8-jdk-alpine
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/discovery-service.jar discovery-service.jar
EXPOSE 8761

RUN apk --update --no-cache add curl
HEALTHCHECK CMD curl --fail http://localhost:8761 || exit 1  
ENTRYPOINT ["java", "-jar", "/discovery-service.jar"]
