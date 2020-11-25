FROM openjdk:8-jdk-alpine
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/gateway-service.jar gateway-service.jar
EXPOSE 8765

RUN apk --update --no-cache add curl
HEALTHCHECK CMD curl --fail http://localhost:8765 || exit 1  
ENTRYPOINT ["java", "-jar", "/gateway-service.jar"]
