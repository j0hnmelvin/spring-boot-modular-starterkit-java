# openjdk:17 supports AMD and ARM (for Apple Silicon) platforms
FROM openjdk:17
# Copy the JAR file from the build process
COPY app/build/libs/*.jar app.jar
# Define the entrypoint to run the application
ENTRYPOINT ["java","-jar","/app.jar"]