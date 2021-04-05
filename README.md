# Credit Card API

This project uses openapi-generator-maven-plugin which will automatically create the model and controller classes.

The generated source code is available at credit-card-api\target\generated-sources\openapi\src\main\java\com\zzz folder.

To run the Spring Boot Application
```
./mvnw clean package
java -jar target/credit-card-api-0.0.1-SNAPSHOT.jar
```
OR
```
./mvnw clean package
./mvnw spring-boot:run
```
Login to the [application](http://localhost:8080) using 
</br>User: user
</br>Password: password

Use swagger-ui to view Credit Card API documentation
http://localhost:8080/swagger-ui.html

Openapi 3.0 supports servers dropdown in swagger-ui. This can be used to test the Credit Card API.
Sample Credit card numbers for testing - 371110201010004, 799273987130000

View Health checks at
http://localhost:8080/actuator/health

Jacoco Code coverage is available at target\site\jacoco\index.html

#Assumptions
* The current code only supports one currency(GBP).
* Minimum Credit card character length is 13.

#Improvements
* The list of currencies and conversion rates could be retrieved from a service.
* Implement Opentracing API to have a full and consistent view of trail of messages/calls in the platform