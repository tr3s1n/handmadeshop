# Handmade Shop Project

This is a simple Java Spring Boot project for an e-commerce web application where my mom will be able to sell her handmade crafts. 
This project is intended to showcase my skills as a Software Developer, and also it serves as a practical application for learning and experimenting with various technologies.

## Table of Contents

- [Changelog](#changelog)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Setting up SSL](#setting-up-ssl)
- [Technologies Used](#technologies-used)
- [Contributing](#contributing)
- [License](#license)

## Changelog
- **18/03/2024**:
Added SSL Configuration, refactored all controllers using Api interfaces.

- **14/03/2024**:
Added token blacklist for expired JWT tokens, new logout and refreshToken endpoints. Updated tests, added first integration test for AuthController.

- **13/03/2024**:
Added JWT Authentication & Authorization and basic setup of Spring Security 6. Added lombok and refactored model classes and data seeder classes.

- **11/03/2024**:
Added tests for all the remaining services and first Controller test for UserController.

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

1. **Clone the repository:**
   ```bash
   git clone https://github.com/your-username/handmade-shop.git
   ```
   
2. **Navigate to the project directory:**
    ```bash
    cd handmade-shop
    ```
   
3. **Prepare application.properties file**
   - Create a copy of example_application.properties and rename it to application.properties.
   - Change all the placeholders:
   ```properties
    spring.datasource.username=$DB_USER_HERE$
    spring.datasource.password=$DB_PASSWORD_HERE$
    spring.security.user.name=$SECURITY_USER_HERE$
    spring.security.user.password=$SECURITY_PASSWORD_HERE$
    ```

4. **Prepare compose.yaml file**
    - Create a copy of example_compose.yaml and rename it to compose.yaml.
    - Use the same credentials that you used in application.properties instead of the placeholders for `POSTGRES_PASSWORD` and `POSTGRES_USER`:
    ```properties
    POSTGRES_PASSWORD=$DB_PASSWORD_HERE$
    POSTGRES_USER=$DB_USER_HERE$
    ```
   
5. **Build the project using gradle in your IDE or using the command:**
    ```bash
    ./gradlew build
    ```

6. **Run the application:**
   ```bash
   ./gradlew bootRun
   ```

- After that, the Spring Boot application will start, and you should be able to access the application port that you set in `server.port` property in `application.properties` file.
- To access Swagger API documentation, visit https://localhost:8443/swagger-ui/index.html


## Setting up SSL

To enable SSL (Secure Sockets Layer) in your Spring Boot application, follow these steps:

1. **Generate Keystore:**
   - Open a terminal or command prompt.
   - Navigate to the directory where you want to generate the keystore file.
   - Run the following `keytool` command to generate the keystore:
     ```bash
     keytool -genkey -alias $SSL_KEYSTORE_ALIAS_HERE$ -storetype PKCS12 -keyalg RSA -keysize 2048 -keystore keystore.p12 -validity 3650
     ```
     This command generates a keystore named `keystore.p12` with the necessary parameters.
     During the keystore generation process, you'll be prompted to enter information such as your name, organization, and location.
     Provide the requested information as needed.

2. **Configure Application:**
   - Provide the keystore alias and password in the application.properties. set app.ssl.enabled to true:
     ```properties
     server.port=8443
     server.ssl.key-store=keystore.p12
     server.ssl.key-store-password=$SSL_KEYSTORE_PASSWORD_HERE$
     server.ssl.keyStoreType=PKCS12
     server.ssl.keyAlias=$SSL_KEYSTORE_ALIAS_HERE$
     app.ssl.enabled=true
     ```

3. **Restart Application:**
   - Restart your Spring Boot application for the changes to take effect.

4. **Verification:**
   - Access your application using `https` protocol (e.g., `https://localhost:8443`) to verify that SSL is enabled.

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
