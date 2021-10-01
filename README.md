## Sales Company Microservices App  

### Project in early stage of development, planned finish - end of november  

System for sales management in company with many shops and warehouses.

### Technology stack  

- Java 16  
- Spring Boot  
- Spring Data JPA (database - MySQL)  
- Spring Web  
- Spring Cloud Gateway Api
- Spring Eureka   
- Spring Security  
- Lombok  

### Implemented services

- Api Gateway
- Eureka Server  
- Users Service (~80% done)
- Products Service (~30% done)
- Order Service (~20% done)

### Planned services  

- shop service  
- warehouse service 

### TODO

### Products service  

- check and test relations in entities  
- write dao's, services, controllers of all domain classes  
- validators of domain classes (+ unit tests of them)  
- !! UNIT TESTS !! 


###Users Service

- write more tests of repository  
- tests of controller  
- Add address domain class and include it to user  
- Make separated classes for customer and admin  
