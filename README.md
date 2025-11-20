# ☕ Coffee Collection API

A robust RESTful API built with Spring Boot to manage a personal coffee collection. This project demonstrates modern Java development practices, including secure authentication, role-based access control, and containerization.

## 🚀 Features

-   **CRUD Operations**: Create, Read, Update, and Delete coffee entries.
-   **Security**:
    -   **JWT Authentication**: Stateless authentication using JSON Web Tokens.
    -   **Database Authentication**: Users stored in PostgreSQL with BCrypt password encryption.
    -   **Role-Based Access Control (RBAC)**:
        -   `ROLE_USER`: Can view and add coffees.
        -   `ROLE_ADMIN`: Can delete coffees.
-   **Validation**: Input validation using Bean Validation (JSR 380).
-   **Documentation**: Interactive API documentation via Swagger UI / OpenAPI.
-   **Containerization**: Fully dockerized application and database using Docker Compose.
-   **Architecture**: Layered architecture (Controller, Service, Repository) with DTOs.

## 🛠️ Tech Stack

-   **Java 17**
-   **Spring Boot 3.3.5** (Web, Data JPA, Security, Validation)
-   **PostgreSQL**
-   **Docker & Docker Compose**
-   **Maven**

## 🏁 Getting Started

### Prerequisites

-   **Docker** (Recommended)
-   **Java 17+** & **Maven** (If running locally without Docker)

### Option 1: Run with Docker (Easiest)

1.  **Clone the repository**:
    ```bash
    git clone https://github.com/stefbou9/CoffeeCollectionAPI.git
    cd CoffeeCollectionAPI
    ```

2.  **Start the application**:
    ```bash
    docker-compose up --build
    ```
    This will start both the API (port 8080) and the PostgreSQL database (port 5432).

3.  **Access the API**:
    The API will be available at `http://localhost:8080`.

### Option 2: Run Locally with Maven

1.  **Ensure PostgreSQL is running** and create a database named `coffee_db`.
2.  **Configure Database**:
    Update `src/main/resources/application.properties` or set environment variables:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/coffee_db
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    ```
3.  **Run the application**:
    ```bash
    ./mvnw spring-boot:run
    ```

## 📖 API Documentation

Once the application is running, you can access the interactive Swagger UI documentation at:

👉 **[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)**

### Authentication Flow
1.  **Register**: POST `/api/auth/register` with `username` and `password`.
2.  **Login**: POST `/api/auth/login` to receive a JWT.
3.  **Authorize**: Click the "Authorize" button in Swagger UI and enter `Bearer <your_token>`.

## 🧪 Running Tests

To run the automated test suite (Unit & Integration tests):

```bash
./mvnw test
```

## 🔒 Security Roles

-   **User**: Can read (`GET`) and create (`POST`, `PUT`) coffees.
    -   *Default role for new registrations.*
-   **Admin**: Has all User permissions plus the ability to `DELETE` coffees.
    -   *Default Admin User*: Username: `admin`, Password: `admin` (Created automatically on startup if missing).

## 📄 License

This project is for learning purposes.