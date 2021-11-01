FROM openjdk:16
EXPOSE 8500
ADD ./target/shop-service.jar shop-service.jar
ENTRYPOINT ["java", "-jar", "shop-service.jar"]