FROM openjdk:17
EXPOSE 8200
ADD ./target/user-service.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]