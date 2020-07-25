FROM openjdk:15-jdk-alpine
LABEL description="it is spring web server for otus homework"
#RUN addgroup -S otusdemo && adduser -S otusspring -G otusdemo
#USER otusspring:otusdemo
WORKDIR /usr/local/myapp
EXPOSE 8000
ARG JAR_FILE=target/demo-0.0.2-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/usr/local/myapp/app.jar", "--server.port=8000"]