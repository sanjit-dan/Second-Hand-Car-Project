FROM openjdk:17

# Copy the correct jar and rename it inside the container
COPY target/final-project.jar /usr/app/final-project.jar

WORKDIR /usr/app/

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "final-project.jar"]
