## Spring Boot Modular Starter Kit (Java)

This project template is your launchpad for building modular Spring Boot applications with Java. It offers a pre-configured foundation with essential elements to streamline your development process. It includes a modular architecture, promoting improved code organization and maintainability with the following modules:
- **app-mongodb** and **app-postgres**
  - Responsible for the main application and YAML files
  - Responsible for integration with other modules
  - Depends on the other modules to assemble the application
  - Spring Profiles:
    - application-mongodb.yml
    - application-mongodb-kafka.yml
    - application-postgres.yml
    - application-postgres-kafka.yml
- **integration-apache-kafka-avro**
  - This is an example module that illustrates integrations with external services using [Apache Kafka](https://kafka.apache.org) (Event Streaming) and [Apache Avro](https://avro.apache.org) (Data Serialization Format)
  - Can depend on the other modules
  - You can implement your custom modules following a similar structure
- **user-service**
  - This example module illustrates how to protect a Java REST API using [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) by restricting access to its resources to authorized accounts only.
  - Depends on no other modules
  - You can implement your custom modules following a similar structure
- **user-service-mongodb** and **user-service-postgres**
  - This example module illustrates database-specific implementation for the user-service module.

## Features

- [x] Containerization:
  - [Docker](https://www.docker.com)
- [x] Databases:
  - [x] Non-Relational Document Store
    - [x] [MongoDB](https://www.mongodb.com)
  - [x] Relational
    - [x] [PostgreSQL](https://www.postgresql.org/)
    - [x] Database Migration Tool
      - [x] [Flyway](https://flywaydb.org/documentation/usage/gradle/)
- [x] Integration Patterns:
  - [x] REST API:
    - [x] Security:
      - [x] [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) - Secure API Endpoints
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
  - [x] [SonarQube](https://plugins.gradle.org/plugin/org.sonarqube) - Comprehensive Static Code Analysis

## Getting Started

### Clone Project
```
git clone https://github.com/j0hnmelvin/spring-boot-modular-starterkit-java.git
```

### Run Application (without Docker)

**1. Build Application JAR**

```
./gradlew clean build
```
This command builds the application JAR file.

**2. Run Application**

For example, the following command runs `app-mongodb` with `mongodb-kafka` as the active Spring profile. 
```
SPRING_PROFILES_ACTIVE=mongodb-kafka \
MONGODB_HOST=mongodb \
MONGODB_DATABASE=spring_boot_modular_starterkit_java \
MONGODB_USERNAME=username \
MONGODB_PASSWORD=password \
KAFKA_BOOTSTRAP_SERVERS=kafka:29092 \
KAFKA_SCHEMA_REGISTRY=http://schema-registry:8081 \
AZURE_AD_CREDENTIAL_CLIENT_ID=PLACEHOLDER \
AZURE_AD_APP_ID_URI=PLACEHOLDER \
./gradlew bootRun
```
Note:Replace the `PLACEHOLDER` values for `AZURE_AD_CREDENTIAL_CLIENT_ID` and `AZURE_AD_APP_ID_URI` with your actual Azure AD credentials before running the application.

### Run Application as Docker Container

**1. Build Application JAR and Docker Images**

```
docker compose -f docker-compose________.yml build
```
For example, `docker compose -f docker-compose-postgres-kafka.yml build` command builds Docker images for the following components based on the configurations defined in [docker-compose-postgres-kafka.yml](./docker-compose-postgres-kafka.yml):
- [PostgreSQL](https://www.postgresql.org/)
- [Apache ZooKeeper](https://zookeeper.apache.org)
- [Apache Kafka](https://kafka.apache.org)
- [Confluent Schema Registry](https://docs.confluent.io/platform/current/schema-registry/index.html)
- spring-boot-modular-starterkit-java-postgres-kafka - **Main Application**
  - Builds application based on the configurations defined in [postgres.Dockerfile](./postgres.Dockerfile).

**2. Run Docker Containers**

```
docker compose -f docker-compose________.yml up -d
```
This command starts the Docker containers in detached mode (-d), running in the background.


**Additional Information**

 If you need to modify the application environment variables, adjust any relevant configuration in `docker-compose________.yml`.