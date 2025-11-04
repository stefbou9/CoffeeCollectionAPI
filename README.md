## ☕ Coffee Collection API

A simple RESTful API built with Spring Boot to create, read, update, and delete entries in a personal coffee collection.

### What this project demonstrates
- **Java 17**
- **Spring Boot**
- **Spring Data JPA (Hibernate)**
- **REST API design**
- **PostgreSQL**
- **Maven**

## 🚀 How to run locally

### Prerequisites
- **Java 17+**
- **Maven** (or use the included Maven Wrapper `mvnw`/`mvnw.cmd`)
- **PostgreSQL** running locally

### 1) Clone the repository
```bash
git clone https://github.com/stefbou9/CoffeeCollectionAPI.git
cd CoffeeCollectionAPI
```

### 2) Configure the database
- Ensure PostgreSQL is running (default: `localhost:5432`).
- Create the database:
```sql
CREATE DATABASE "CoffeeCollectionDB";
```
- Create `src/main/resources/application-local.properties` for local secrets (git-ignored):
```properties
# src/main/resources/application-local.properties
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password
```

This project uses the `local` profile by default for development (`spring.profiles.active=local`).

### 3) Run the application
- From your IDE: run `CoffeeCollectionApiApplication`.
- Or via terminal (Maven Wrapper):
```bash
# Windows
./mvnw.cmd spring-boot:run

# macOS/Linux
./mvnw spring-boot:run
```

The API will be available at `http://localhost:8080`.

## ⚙️ API Endpoints
Base URL: `http://localhost:8080`

- **POST** `/api/coffees` — Add a new coffee
- **GET** `/api/coffees` — List all coffees
- **GET** `/api/coffees/{id}` — Get a coffee by ID
- **PUT** `/api/coffees/{id}` — Update a coffee by ID
- **DELETE** `/api/coffees/{id}` — Delete a coffee by ID

### Example request body
Use this format for `POST /api/coffees` and `PUT /api/coffees/{id}`.
```json
{
  "name": "Yirgacheffe",
  "origin": "Ethiopia",
  "roast": "LIGHT",
  "price": 22.50,
  "tastingNotes": "Blueberry, lemon, floral"
}
```

Roast options: `LIGHT`, `MEDIUM`, `DARK`.

## 🧰 Configuration
Key properties (see `src/main/resources/application.properties`):
```properties
spring.application.name=CoffeeCollectionAPI
spring.profiles.active=local
spring.datasource.url=jdbc:postgresql://localhost:5432/CoffeeCollectionDB
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

For local secrets, use `application-local.properties` as shown above, or environment variables.

## 📄 License
This repository is for learning purposes.