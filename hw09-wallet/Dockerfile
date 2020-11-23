FROM openjdk:15-jdk-alpine
LABEL description="wallet app for saga"
WORKDIR /usr/local/myapp
EXPOSE 8080
ARG JAR_FILE=build/libs/hw09-wallet.jar
COPY ${JAR_FILE} app.jar
WORKDIR /usr/local/myapp/resources/main/
COPY wallet.db wallet.db
RUN apk update \
    && apk add sqlite \
    && apk add socat
ENTRYPOINT ["java","-jar","/usr/local/myapp/app.jar", "--server.port=8080"]