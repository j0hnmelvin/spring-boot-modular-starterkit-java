# Multi-stage builds

FROM gradle:jdk17 AS build-stage
# Set working directory
WORKDIR /
# Copy project files (excluding target directory)
COPY . .
# Run Gradle clean and build tasks
RUN gradle clean build

# Switch to a slimmer runtime image
# amd64 and arm64 (for Apple Silicon) are common target architectures for openjdk:17 / openjdk:17-jdk-slim
FROM openjdk:17-jdk-slim
# Set working directory
WORKDIR /
# Copy JAR file from build stage
COPY --from=build-stage /app/build/libs/*.jar app.jar
# Define the entrypoint to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
