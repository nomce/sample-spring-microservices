FROM openjdk:8-jdk-alpine
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/account-service.jar account-service.jar
EXPOSE 2222

RUN apk --update --no-cache add curl
HEALTHCHECK CMD curl --fail http://localhost:2222 || exit 1  
ENTRYPOINT ["java", "-jar", "/account-service.jar"]