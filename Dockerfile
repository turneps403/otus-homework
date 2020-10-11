FROM openjdk:15-jdk-alpine
LABEL description="it is spring web server for otus homework"
RUN addgroup -S otusdemo && adduser -S otusspring -G otusdemo
USER otusspring:otusdemo
WORKDIR /usr/local/myapp
EXPOSE 8000
ARG JAR_FILE=build/libs/otus-homework-1.0.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/usr/local/myapp/app.jar", "--server.port=8000"]