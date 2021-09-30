FROM openjdk:16
EXPOSE 8400
ADD ./target/order-service.jar order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]