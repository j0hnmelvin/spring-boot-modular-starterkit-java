# Multi-stage builds

FROM gradle:jdk17 AS build-stage
# Set working directory
WORKDIR /
# Copy project files (excluding target directory)
COPY . .
# Run Gradle clean and build tasks
RUN gradle clean build

# Switch to a slimmer runtime image
# amd64, arm64v8 (for Apple Silicon) and windows-amd64 are the supported architectures for openjdk
FROM openjdk:17-jdk-slim
# Set working directory
WORKDIR /
# Copy JAR file from build stage
COPY --from=build-stage /app-job-marketplace/build/libs/*.jar app.jar
# Define the entrypoint to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
