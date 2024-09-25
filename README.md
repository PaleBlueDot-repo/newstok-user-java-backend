

# News User Backend 

This project is a **News Reels Backend Service** built using **Spring Boot**, designed to handle requests from the frontend for a TikTok-like news reel application. It includes features like **reel likes**, **watch time tracking**, **user profile management**, and **admin-backend interactions** for fetching reel recommendations. The application uses **Spring Security**, **Eureka Server** for service discovery, and **API Gateway** for request routing.

---

## Table of Contents
- [Features](#features)
- [Architecture](#architecture)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Run the Application](#run-the-application)
- [Configuration (application.properties)](#configuration)
- [Security](#security)
- [Eureka Server](#eureka-server)
- [API Gateway](#api-gateway)
- [Contributing](#contributing)

---

## Features

- **User Reels Management**: Handles user requests for liking news reels and tracking watch time.
- **Profile Management**: Provides users with functionality to edit their profiles.
- **Admin Requests**: Sends requests to the admin backend for personalized reel recommendations.
- **Spring Security**: JWT-based authentication and authorization.
- **Eureka Server**: Enables service discovery for microservices.
- **API Gateway**: Routes requests to appropriate services.
- **Spring Data JPA**: Manages SQL database interactions for user and reel data.

---

## Architecture

The backend is responsible for managing all user interactions with news reels, including:
- **Reel Interactions**: Liking a reel, counting watch time, and retrieving reel recommendations.
- **Profile Management**: Allowing users to update profile information.
- **Admin Integration**: Fetching reel recommendations from the admin backend for users.
- **JWT Authentication**: Secure endpoints using JWT tokens.

---

## Prerequisites

Ensure the following are installed:
- Java 17+
- Maven 3.6+
- MySQL or PostgreSQL database
- Eureka Server for service discovery

---

## Installation

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/news-reels-backend.git
   cd news-reels-backend
   ```

2. **Set up the database**:
   - Create a database named `userdb`.
   - Update the database configuration in `application.properties`:
     ```properties
     spring.datasource.url=jdbc:mysql://localhost:3306/userdb
     spring.datasource.username=your_username
     spring.datasource.password=your_password
     ```

3. **Install dependencies**:
   ```bash
   mvn clean install
   ```

4. **Run the Eureka Server, API Gateway, and User Reels Service**.

---

## Run the Application

1. **Run the Eureka Server**:
   ```bash
   mvn spring-boot:run -pl eureka-server
   ```

2. **Run the API Gateway**:
   ```bash
   mvn spring-boot:run -pl api-gateway
   ```

3. **Run the User Reels Service**:
   ```bash
   mvn spring-boot:run -pl user-reels-service
   ```


## Configuration (`application.properties`)

Here’s a breakdown of the key properties in the `application.properties` file:

### Database Configuration
```properties
spring.application.name=user-reels-service
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.url=jdbc:mysql://localhost:3306/userdb
spring.datasource.username=<db_username>
spring.datasource.password=<db_password>
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
```
- **spring.application.name**: Name of the service as registered with Eureka.
- **spring.datasource.driver-class-name**: MySQL database driver.
- **spring.datasource.url**: URL for connecting to the MySQL database.
- **spring.datasource.username**: Username for the database.
- **spring.datasource.password**: Password for the database.
- **spring.jpa.show-sql**: Enables SQL logging.
- **spring.jpa.hibernate.ddl-auto**: Automatically updates the database schema.

### Security Configuration
```properties
security.jwt.secret-key=<jwt_secret_key>
security.jwt.expiration-time-ms=86400000
security.jwt.issuer=NewsTokAPI
```
- **security.jwt.secret-key**: Secret key used for signing JWT tokens (sensitive value).
- **security.jwt.expiration-time-ms**: Expiration time for JWT tokens (in milliseconds).
- **security.jwt.issuer**: The issuer of the JWT token (e.g., NewsTokAPI).

### Admin Integration
```properties
UserToAdminAuthentication.email=user@example.com
UserToAdminAuthentication.password=<user_password>

AdminToUserAuthentication.email=admin@example.com
AdminToUserAuthentication.password=<admin_password>

admin.login.api.url=http://localhost:8080/admin/login
```
- **UserToAdminAuthentication**: Email and password used by the user backend to authenticate with the admin backend.
- **AdminToUserAuthentication**: Email and password used by the admin backend to authenticate with the user backend.
- **admin.login.api.url**: URL for the admin login API used by the backend.

---

## Security

- **Spring Security** secures all endpoints with JWT authentication.
- The user must log in to receive a JWT token, which will be used in the `Authorization` header for each subsequent request.

---

## Eureka Server

- **Eureka Server** provides service discovery, ensuring that microservices (like the API Gateway and User Reels Service) can find each other dynamically.
- You can access the Eureka dashboard at:
  ```
  http://localhost:8761
  ```

---


=
