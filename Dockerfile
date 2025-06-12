FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app
COPY target/*.jar app.jar
COPY src/main/resources/slack_clone.db slack_clone.db
EXPOSE 5000
CMD ["java", "-jar", "app.jar"]