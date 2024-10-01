#FROM ubuntu:latest
#LABEL authors="kallarakshana"
#
#ENTRYPOINT ["top", "-b"]
#
##
## Build stage
##
#FROM maven:3.8.2-jdk-17 AS build
#COPY . .
#RUN mvn clean package -Pprod -DskipTests
#
##
## Package stage
##
#FROM openjdk:11-jdk-slim
#COPY --from=build /target/demo-0.0.1-SNAPSHOT.jar demo.jar
## ENV PORT=8080
#EXPOSE 8080
#ENTRYPOINT ["java","-jar","demo.jar"]


# Base image for building the application
FROM maven:3.8.2-openjdk-17 AS build
LABEL authors="kallarakshana"

# Set the working directory
WORKDIR /app

# Copy the pom.xml and source code to the container
COPY pom.xml .
COPY src ./src

# Clean and package the application, skipping tests
RUN mvn clean package -Pprod -DskipTests

# Second stage: use a slim Java image for the runtime environment
FROM openjdk:17-jdk-slim

# Set the working directory in the runtime image
WORKDIR /app

# Copy the packaged jar from the build stage
COPY --from=build /app/target/task-0.0.1-SNAPSHOT.jar /app/task.jar

# Expose the port for the application
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "task.jar"]
