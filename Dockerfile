FROM eclipse-temurin:11.0.20_8-jdk
WORKDIR /app
CMD ["./gradlew", "clean", "build"]
COPY build/libs/*.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "/app/app.jar"]
