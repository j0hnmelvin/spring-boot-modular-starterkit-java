## Description

This is a Spring Boot Gradle Java Multi Module boilerplate with the following modules:
- **app**
  - Responsible for the main application and YAML files
  - Responsible for integration with other modules
  - Depends on the other modules to assemble the application
- **integration-apache-kafka-avro**
  - This example module illustrates integrations with external services using [Apache Kafka](https://kafka.apache.org) (Event Streaming) and [Apache Avro](https://avro.apache.org) (Data Serialization Format)
  - Can depend on the other modules
- **user-service**
  - This example module illustrates how to protect a Java REST API using [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) by restricting access to its resources to authorized accounts only.
  - Depends on no other modules

## Features
- [x] Static Code Analysis (SCA) Tools: 
  - [x] [Spotless](https://github.com/diffplug/spotless) - Code Formatter
  - [x] [JaCoCo](https://github.com/jacoco/jacoco) - Java Code Coverage Library
  - [x] [SonarQube](https://plugins.gradle.org/plugin/org.sonarqube) - Static Code Analysis
- [x] Boilerplate Code Generation Tools:
  - [x] [Project Lombok](https://hibernate.org/validator/)
  - [x] [MapStruct](https://mapstruct.org)
- [x] Data Validation Library:
  - [x] [Hibernate Validator Engine](https://hibernate.org/validator/) - Java Bean Validation
- [x] Database:
  - [x] [PostgreSQL](https://www.postgresql.org/)
- [x] Integration Patterns:
  - [x] REST API:
    - [x] [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) - Protect Java Web API
    - [x] [SpringDoc OpenAPI](https://springdoc.org) - Code-Driven API Documentation
  - [x] Event-Driven Architecture (EDA):
    - [x] [Apache Kafka](https://kafka.apache.org) - Event Streaming
    - [x] [Apache Avro](https://avro.apache.org) - Data Serialization Format