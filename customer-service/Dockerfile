FROM openjdk:8-jdk-alpine
MAINTAINER Piotr Minkowski <piotr.minkowski@gmail.com>
MAINTAINER Ramazan Sakin <ramazansakin63@gmail.com>
ADD target/customer-service.jar customer-service.jar
EXPOSE 3333

ADD wait-for.sh wait-for.sh
RUN apk --update --no-cache add curl netcat-openbsd
HEALTHCHECK CMD curl --fail http://localhost:3333 || exit 1
ENTRYPOINT ["./wait-for.sh", "account-service:2222", "--", "java", "-jar", "/customer-service.jar"]