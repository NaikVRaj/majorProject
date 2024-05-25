FROM openjdk:17
WORKDIR /app
ADD target/workwise-0.0.1-SNAPSHOT.jar /app/workwise-0.0.1-SNAPSHOT.jar
EXPOSE 8086
CMD ["java", "-jar", "/app/workwise-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]