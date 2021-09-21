FROM openjdk:16
EXPOSE 8200
ADD ./target/users-service.jar users-service.jar
ENTRYPOINT ["java", "-jar", "users-service.jar"]