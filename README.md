# student-netflix-oss

#Spring Cloud - student-netflix-oss Example

##Introduction

This repo contains an example of Sping cloud (Netflix OSS) micro-services architecture.

The main objective is to understand the spring cloud ecosystem. 

The main parts are (they each have a readme.md with more details):

1. [Eureka] (https://github.com/sree369/student-netflix-oss/tree/master/eureka-naming-server)
2. [Cloud Config Service] (https://github.com/sree369/student-netflix-oss/tree/master/cloud-config-server)
3. [Microservice Gradation Service: This is the server micro-service for Marks service] (https://github.com/sree369/student-netflix-oss/tree/master/gradation-service)
4. [Microservice Marks Service: This is the server micro-service for Subject service] (https://github.com/sree369/student-netflix-oss/tree/master/marks-service)
5. [Microservice Subject Service: This is the micro-service for Student service] (https://github.com/sree369/student-netflix-oss/tree/master/subject-service)
6. [Microservice Student Service: This is the client micro-service] (https://github.com/sree369/student-netflix-oss/tree/master/student-service)
7. Zuul (https://github.com/sree369/student-netflix-oss/tree/master/zuul-api-gateway-server)
8. Distributed Tracing with Spring Sleuth, Zipkin, RabbitMQ, Zipkin dashboard.
9. Load Balancing with Ribbon and Feign

The services can be started in any other but it recommended to start them in above order.

Student Service exposes endpoints that in turn call Subject service endpoints, Subject service will call Marks service endpoints and Marks service will call Gradation service endpoints. The client services find the server services via Eureka.

There is further documentation for each component in the links above.

Note: This example is using Spring Boot version 2.1.4.RELEASE and Spring Cloud version 2.1.3.RELEASE.