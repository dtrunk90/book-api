# Book API – A Simple Demo Project

This is a demo RESTful API for managing books, built to showcase modern Java backend development practices using the Spring ecosystem.

## Features

* Some CRUD operations for books
* RESTful API design with Spring Web
* Spring Boot for rapid setup and configuration
* Spring Data JPA for data persistence 
* In-memory H2 database for development and testing 
* JUnit 5 with Spring Test for integration testing 
* OpenAPI / Swagger documentation 
* OCI-compliant image build via Spring Boot's build-image task
* Clean and modular project structure

This project is intended for demonstration purposes only and is not production-ready.

## Getting Started

### Prerequisites

* JDK 21

### Run the Application

```shell
./mvnw spring-boot:run
```

The API will be available at:

[http://localhost:8080](http://localhost:8080)

### API Documentation (Swagger UI)

Once the application is running, you can access the API documentation at:

[http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)

### Running Tests

```shell
./mvnw test
```

### Jenkins Pipelines

To start a local Jenkins instance with preconfigured jobs for this project, run:

```shell
docker compose up -d
```

Jenkins will be available at:

[http://localhost:8081](http://localhost:8081)

Two pipelines will be created automatically:

1. **book-api-ci (Jenkinsfile)**<br>
   Performs a full build with:<br>
   **Code Analysis:** runs Checkstyle, PMD, and SpotBugs<br>
   **Tests:** executes unit/integration tests with JUnit<br>
   **Packaging:** builds the .jar and archives it<br>
2. **book-api-security-scan (dependencyCheck.Jenkinsfile)**<br>
   Runs OWASP Dependency-Check and publishes the report as part of the job results.
