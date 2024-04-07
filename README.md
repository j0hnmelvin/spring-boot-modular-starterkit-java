## Spring Boot Modular Starter Kit (Java)

This project template is your launchpad for building modular Spring Boot applications with Java. It offers a pre-configured foundation with essential elements to streamline your development process. It includes a modular architecture, promoting improved code organization and maintainability with the following modules:
- **app**
  - Responsible for the main application and YAML files
  - Responsible for integration with other modules
  - Depends on the other modules to assemble the application
- **integration-apache-kafka-avro**
  - This is an example module that illustrates integrations with external services using [Apache Kafka](https://kafka.apache.org) (Event Streaming) and [Apache Avro](https://avro.apache.org) (Data Serialization Format)
  - Can depend on the other modules
  - You can implement your custom modules following a similar structure
- **user-service**
  - This example module illustrates how to protect a Java REST API using [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) by restricting access to its resources to authorized accounts only.
  - Depends on no other modules
  - You can implement your custom modules following a similar structure

## Features

- [x] Containerization:
  - [Docker](https://www.docker.com)
- [x] Database:
  - [x] [PostgreSQL](https://www.postgresql.org/)
  - [x] [Flyway](https://flywaydb.org/documentation/usage/gradle/) - Database Migration Tool
- [x] Integration Patterns:
  - [x] REST API:
    - [x] [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) - Protect Java Web API
    - [x] [SpringDoc OpenAPI](https://springdoc.org) - Code-Driven API Documentation
  - [x] Event-Driven Architecture (EDA):
    - [x] [Apache Kafka](https://kafka.apache.org) - Event Streaming
    - [x] [Apache Avro](https://avro.apache.org) - Data Serialization Format
- [x] Boilerplate Code Generation Tools:
  - [x] [Project Lombok](https://hibernate.org/validator/)
  - [x] [MapStruct](https://mapstruct.org)
- [x] Data Validation Library:
  - [x] [Hibernate Validator Engine](https://hibernate.org/validator/) - Java Bean Validation
- [x] Static Code Analysis (SCA) Tools:
  - [x] [Spotless](https://github.com/diffplug/spotless) - Code Formatter
  - [x] [JaCoCo](https://github.com/jacoco/jacoco) - Java Code Coverage Library
  - [x] [SonarQube](https://plugins.gradle.org/plugin/org.sonarqube) - Static Code Analysis

## Getting Started

### Clone Project
```
git clone https://github.com/j0hnmelvin/spring-boot-modular-starterkit-java.git
```

### Run Application (without Docker)

1. Build Application JAR
    ```
    ./gradlew clean build
    ```
    This command builds the application JAR file.


2. Run Application
    ```
    POSTGRES_HOST=localhost \
    POSTGRES_USER=postgres \
    POSTGRES_PASSWORD=postgres \
    POSTGRES_DB=spring_boot_modular_starterkit_java \
    KAFKA_BOOTSTRAP_SERVERS=localhost:9092 \
    KAFKA_SCHEMA_REGISTRY=http://localhost:8081 \
    AZURE_AD_CREDENTIAL_CLIENT_ID= \
    AZURE_AD_APP_ID_URI= \
    ./gradlew bootRun
    ```
    This command builds the application JAR file.

### Run Application as Docker Container

1. Build Application JAR
    
    ```
    ./gradlew clean build
    ```
    This command builds the application JAR file.

    
2. Build Docker Images

    ```
    docker compose build
    ```
    This command builds Docker images for the following components based on the configurations defined in [docker-compose.yml](./docker-compose.yml):
    - [PostgreSQL](https://www.postgresql.org/)
    - [Apache ZooKeeper](https://zookeeper.apache.org)
    - [Apache Kafka](https://kafka.apache.org)
    - [Confluent Schema Registry](https://docs.confluent.io/platform/current/schema-registry/index.html)
    - **spring-boot-gradle-java-multi-module-boilerplate-application** - The main application.

 
3. Run Docker Containers

    ```
    docker compose up -d
    ```
    This command starts the Docker containers in detached mode (-d), running in the background.


4. Additional Information

   If you need to modify the application environment variables, adjust any relevant configuration in [docker-compose.yml](./docker-compose.yml).