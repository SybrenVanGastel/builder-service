FROM openjdk:8-jdk-alpine
EXPOSE 8052
COPY . /
ENTRYPOINT ["java","-jar","/app.jar"]
