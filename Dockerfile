# Use OpenJDK image
FROM eclipse-temurin:23-jdk-alpine

# Set working directory
WORKDIR /app

# Copy the jar file (ensure it's built with `./mvnw package` or `mvFROM openjdk:17-jdk-slimn package`)
COPY target/fanout-0.0.1-SNAPSHOT.jar app.jar

# Expose application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
