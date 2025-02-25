FROM openjdk:17-jre
COPY build/libs/tr-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
