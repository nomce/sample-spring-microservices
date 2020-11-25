FROM openjdk:8-jdk-alpine
MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/*.jar zipkin-service.jar
EXPOSE 9411

RUN apk --update --no-cache add curl
HEALTHCHECK CMD curl --fail http://localhost:9411 || exit 1  
ENTRYPOINT ["java", "-jar", "/zipkin-service.jar"]
