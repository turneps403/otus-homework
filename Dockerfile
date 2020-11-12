FROM openjdk:15-jdk-alpine
LABEL description="it is spring web server for otus homework"
WORKDIR /usr/local/myapp
EXPOSE 8080
ARG JAR_FILE=build/libs/homework07-1.0.jar
COPY ${JAR_FILE} app.jar
WORKDIR /usr/local/myapp/resources/main/
COPY sqlitesample.db sqlitesample.db
RUN apk update \
    && apk add sqlite \
    && apk add socat
ENTRYPOINT ["java","-jar","/usr/local/myapp/app.jar", "--server.port=8080"]