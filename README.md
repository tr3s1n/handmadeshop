# Handmade Shop Project

This is a simple Java Spring Boot project for an e-commerce web application where my mom will be able to sell her handmade crafts. 
This project is intended to showcase my skills as a Software Developer and also it serves as a practical application for learning and experimenting with various technologies.

## Table of Contents

- [Changelog](#changelog)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Changelog
- **13/03/2024**:
Added JWT Authentication & Authorization and basic setup of Spring Security 6. Added lombok and refactored model classes and data seeder classes.

- **11/03/2024**:
Added tests for all the remaining services, test for UserController and example_test.properties.

- **13/12/2023**:
Added dependencies for Unit testing, created first Test class for Product service using Mockito and JUnit.

- **10/12/2023**:
Updated README.md (Added this changelog :D), Added constructors to all the entities in the model package, added data package with DataSeeder classes for creating dummy testing data after application is started. Added Spring Security user and password in the example configuration files.

- **9/10/2023**:
Fixed build issues and table names for order and user as they were a reserved keyword in postgreSQL.

- **8/10/2023**:
Added Controller, DTO, Service, Repository classes for the remaining entities. Added logging and custom exceptions together with GlobalExceptionHandler.

- **5/10/2023**:
Added Controller, DTO, Service and Repository classes for Product entity.

- **3/10/2023**:
Added Controller, DTO, Service and Repository classes for User entity.

- **2/10/2023**:
Created the repository and generated starter Spring boot 3.2.0 project structure together with creation of all the needed entities in the model package, setup postgres database running on docker and tested connection between the app and the database.

## Prerequisites

Make sure you have the following installed:

- Java Development Kit (JDK) 21
- Gradle 8.5 - possible to use the wrapper that is included but you can also use local installation
- Docker Desktop

## Getting Started

1. Clone the repository:

   ```bash
   git clone https://github.com/your-username/handmade-shop.git
   ```
   
2. Navigate to the project directory:

   ```bash
   cd handmade-shop
   ```
   
3. Create a copy of example_application.properties and rename it to application.properties. Change all the placeholders:

   ```bash
   spring.datasource.username=$DB_USER_HERE$
   spring.datasource.password=$DB_PASSWORD_HERE$
   spring.security.user.name=$SECURITY_USER_HERE$
   spring.security.user.password=$SECURITY_PASSWORD_HERE$
   ```

4. For running tests without issues, create a copy of example_test.properties located in /src/test/resources and rename it to test.properties. Change all the placeholders:

   ```bash
   test.username=$SECURITY_USER_HERE$
   test.password=$SECURITY_PASSWORD_HERE$
   ```   

5. create a copy of example_compose.yaml and rename it to compose.yaml. Update the placeholders. Use the same credentials that you used in application.properties. Update rows:

   ```bash
      - 'POSTGRES_PASSWORD=$DB_PASSWORD_HERE$'
      - 'POSTGRES_USER=$DB_USER_HERE$'
   ```
   
7. Build the project using gradle in your IDE or using the command:

   ```bash
   ./gradlew build
   ```

8. Run the application:

   ```bash
   ./gradlew bootRun
   ```

After that the Spring Boot application will start and you should be able to access the application at http://localhost:8080 and send requests to API. Use the credentials you used in *application.properties* for *spring.security* settings.

## Technologies Used

- Spring Boot 3.2.0
- Java 21
- Gradle 8.5
- Docker Desktop
- PostgreSQL (latest docker image)
- Hibernate
- Spring Security 6
- Mockito and JUnit5

## Contributing

Feel free to contribute to the project by submitting bug reports, feature requests, or pull requests.

1. Fork the project.
2. Create a new branch: `git checkout -b feature-name`.
3. Commit your changes: `git commit -m 'Add some feature'`.
4. Push to the branch: `git push origin feature-name`.
5. Submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).
