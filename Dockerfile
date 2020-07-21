FROM openjdk:15-jdk-alpine
LABEL description="just for example"
RUN addgroup -S otusdemo && adduser -S otusspring -G otusdemo
USER otusspring:otusdemo
WORKDIR /usr/local/myapp
EXPOSE 8080
ARG JAR_FILE=target/demo-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/usr/local/myapp/app.jar"]