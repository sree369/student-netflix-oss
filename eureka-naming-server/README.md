#student-netflix-oss - Eureka

##Introduction

This module creates an Eureka server to be used by the services. By default it it configures to run in localhost.

To open the Eureka status page:

http://localhost:8761/

Once the Eureka service is running it is ready to accept the registration of other services. This will happen automatically for all the services that are configured to register with the Eureka server.

##Configuration

###application.properties

In this file we place the configuration for the Eureka server. Things like the port it should use, whether it should register itself as a service, etc.