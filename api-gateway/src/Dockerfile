FROM openjdk:16
EXPOSE 8080
ADD ./target/api-gateway.jar api-gateway.jar
ENTRYPOINT ["java", "-jar", "api-gateway.jar"]