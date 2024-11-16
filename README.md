# WEATHER TRACER API SERVER

### Visual Studio Code
- `Extension Pack for Java` https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack

### Spring Initialize (https://start.spring.io/)
- `Project` Maven
- `Language` Java
- `Spring Boot` 3.3.4
- `Packaging` Jar
- `Java` 21
- `Dependencies` Spring Web

### Quick Start
1. Start server.
    ```bash
    $ ./mvnw spring-boot:run
    ```
2. Browse to http://localhost:5000/api/v1/swagger-ui.html and you will see the Swagger page.

### Maven Command
- `./mvnw spring-boot:run` Run project code
- `./mvnw clean` Clean project
- `./mvnw compile` Compile project code
- `./mvnw test-compile` Compile unit test code
- `./mvnw test` Run unit tests
- `./mvnw package` Package project into a JAR file
- `./mvnw install` Install project into local repository

### Make Command
- `make build_image` Build Docker image
- `make push_image` Push Docker image

### Directory
```
├─.mvn ------------------------------------- Directory for Maven related configuration files and scripts
├─.vscode ---------------------------------- Directory for Visual Studio Code related configuration files
├─k8s -------------------------------------- Directory for Kubernetes manifest files
├─lib -------------------------------------- Directory for external libraries or dependency JAR files
├─src -------------------------------------- Root directory for source code and resource files
│  ├─main ---------------------------------- Directory for the application's actual source code and resource files
│  │  ├─java ------------------------------- Directory for the application's Java source code
│  │  │  └─com ----------------------------- Root directory for Java package structure
│  │  │     └─weathertracer ---------------- Package namespace corresponding to the organization or company name
│  │  │        └─v1 ------------------------ Package namespace representing the project or module name
│  │  │           ├─config ----------------- Directory for the application's configuration files
│  │  │           ├─constant --------------- Directory for constants defined in the application
│  │  │           ├─controller ------------- Directory for controller classes handling the application's API requests
│  │  │           ├─dto -------------------- Directory for Data Transfer Objects
│  │  │           ├─service ---------------- Directory for service classes responsible for business logic
│  │  │           │  └─impl ---------------- Directory for classes implementing service interfaces
│  │  │           └─vo --------------------- Directory for Value Objects, classes used for data transfer
│  │  └─resources -------------------------- Directory for the application's configuration files and static resources
│  └─test ---------------------------------- Directory for test code
└─target ----------------------------------- Directory for Maven build results
```