# microservices-basics
repository covering basic microservice concepts  using EUreka for proxy server or service discovery ZUUL for Gateway API feign for data communicarion between microservices kafka for message based communication

# Microservices basic concepts  Design

This is the solution to create couple of microservices namely #User profile service and #Authentication service and register them to #
Eureka service discovery

## Functionality of microservices:

- The client uses Authentication service as both Gateway API and User authentication based on db based user authentication
- User profile service is used to perform operations on user profiles
- Kafka is used for event based communication for update and delete of profiles 
- Open feign for communication between services
- ZUUL for API Gateway
- Eureka for service discovery
- Spring security for schema based authentication




### System Requirements:
- Java 1.8
- Apache Maven 
- Sprinboot 2
- Apache Kafka


   
### Built With

- [Maven](https://maven.apache.org/) - Build/Dependency Management





