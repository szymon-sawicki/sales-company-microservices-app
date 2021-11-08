FROM openjdk:11
EXPOSE 8888
WORKDIR ./microservice
ADD target/app.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]