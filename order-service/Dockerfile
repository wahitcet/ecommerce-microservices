# Start with a base image containing Java 22 runtime
FROM openjdk:22-jdk-alpine

# Add Maintainer Info
LABEL maintainer="pttEm@example.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8082 available to the world outside this container
EXPOSE 8082

# The application's jar file
ARG JAR_FILE=target/*.jar

# Add the application's jar to the container
ADD ${JAR_FILE} app.jar

# Run the jar file
ENTRYPOINT ["java","-jar","/app.jar"]
