FROM openjdk
WORKDIR /workwize/backend
ADD target/workwize-0.0.1-SNAPSHOT.jar /workwize/backend
EXPOSE 8086
CMD ["java", "-jar", "workwize-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=docker"]
