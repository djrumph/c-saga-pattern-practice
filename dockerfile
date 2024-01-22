# Use a base image with OpenJDK and Maven
FROM eclipse-temurin:17 as builder

# Set the working directory
WORKDIR /app

# Copy the Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Copy the POM file
COPY pom.xml .

# Copy the project source
COPY src src

# Build the application
RUN ./mvnw clean package -DskipTests

# Use a smaller base image for the runtime
FROM eclipse-temurin:17-jre-jammy

# Set the working directory
WORKDIR /app

# Copy the JAR file from the builder image
COPY target/spring-boot-0.0.1-SNAPSHOT.jar app.jar

# Expose the port
EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "app.jar"]
