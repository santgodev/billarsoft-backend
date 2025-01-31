FROM openjdk:21-jdk-slim


COPY target/billarsoft-0.0.1-SNAPSHOT.jar spring_app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "spring_app"]
