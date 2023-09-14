# I Was a Car REST API Server

## Tech Stack

* Java 11
* Gradle 8.2.1
* Spring Boot 2.7.14
* MySQL 8.0.32
* Query DSL 5.0.0

![tech_stack](https://user-images.githubusercontent.com/38150034/267848976-af626a5a-269a-4596-adf8-df8401cd3c5a.png)

## Architecture

![architecture](https://user-images.githubusercontent.com/38150034/267847290-6714accf-0203-4053-85fa-96d474e5c132.png)

## CI/CD Pipeline

![CICD](https://user-images.githubusercontent.com/38150034/267837841-e16ffad5-e2a7-4bc6-b1d6-1dcf6c23b205.png)

## Run

### Local Server

```bash
./gradlew clean build

java -jar -Dspring.profiles.active=local build/libs/api-0.0.1-SNAPSHOT.jar
```

### Development Server

```bash
./gradlew clean build

java -jar -Dspring.profiles.active=dev build/libs/api-0.0.1-SNAPSHOT.jar
```

### Production Server

```bash
./gradlew clean build

java -jar -Dspring.profiles.active=prod build/libs/api-0.0.1-SNAPSHOT.jar
```

