FROM openjdk:16
EXPOSE 8300
ADD ./target/products-service.jar products-service.jar
ENTRYPOINT ["java", "-jar", "products-service.jar"]