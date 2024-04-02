## Description

This is a Spring Boot Gradle Java Multi Module boilerplate with the following modules:
- **app**
    - Responsible for the main application and YAML files
    - Responsible for integration with other modules
    - Depends on the other modules to assemble the application
- **microsoft-entra-ID-protect-resource-server**
  - This example module illustrates how to protect a Java Web API using Microsoft Entra ID by restricting access to its resources to authorized accounts only.

## Features
- [x] [Spotless](https://github.com/diffplug/spotless) - Code Formatter
- [x] [JaCoCo](https://github.com/jacoco/jacoco) - Java Code Coverage Library
- [x] [SonarQube](https://plugins.gradle.org/plugin/org.sonarqube) - Static Code Analysis
- [x] [Project Lombok](https://hibernate.org/validator/) - Reduces Boilerplate Code
- [x] [Hibernate Validator Engine](https://hibernate.org/validator/) - Java Bean Validation
- [x] [SpringDoc OpenAPI](https://springdoc.org) - Code-Driven API Documentation
- [x] [Microsoft Entra ID](https://learn.microsoft.com/en-us/azure/developer/java/spring-framework/spring-boot-starter-for-azure-active-directory-developer-guide?tabs=SpringCloudAzure5x) - Protect Java Web API 