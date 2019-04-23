#student-netflix-oss - Config Service

##Introduction

This sevice will keep the configuration to be read by the micro-services. The objective is to have one centralized place to store the configuration.

##Configuration

###application.properties

This file contains the name of the application and where to find the configuration files.

The configuration files are stored in the following [git repository] (https://github.com/sree369/student-netflix-oss/microservices-localconfig-repo).

This file contains configurations for the port in which it should run, the eureka server to use, and the Actuator management path.