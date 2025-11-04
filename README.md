☕ Coffee Collection API

This is a simple RESTful API built with Spring Boot to create, read, update, and delete entries in a personal coffee collection.

This project was created as a learning exercise to understand the fundamentals of:

Java

Spring Boot

Spring Data JPA (Hibernate)

REST API design

PostgreSQL

Maven

🚀 How to Run Locally

Prerequisites

Java (17 or newer)

Maven

A running PostgreSQL server

Setup Instructions

Clone the Repository:

git clone [https://github.com/stefbou9/CoffeeCollectionAPI.git](https://github.com/stefbou9/CoffeeCollectionAPI.git)
cd CoffeeCollectionAPI


Configure Database:

Ensure your PostgreSQL server is running (e.g., on localhost:5432).

Create a new database for the project (e.g., CREATE DATABASE "CoffeeCollectionDB";).

Create a file at src/main/resources/application-local.properties to store your local credentials (this file is ignored by Git).

Add your database username and password to this new file:

# src/main/resources/application-local.properties
spring.datasource.username=your_postgres_username
spring.datasource.password=your_postgres_password


Run the Application:

Run the app directly from your IDE by executing the CoffeeCollectionApiApplication.java file.

Alternatively, use the Maven wrapper from your terminal:

# On Windows
./mvnw spring-boot:run

# On macOS/Linux
./mvnw spring-boot:run


The API will start and be available at http://localhost:8080.

⚙️ API Endpoints

All endpoints are relative to the base URL: http://localhost:8080

Method

Endpoint

Description

POST

/api/coffees

Adds a new coffee to the collection.

GET

/api/coffees

Retrieves a list of all coffees.

GET

/api/coffees/{id}

Retrieves a single coffee by its ID.

PUT

/api/coffees/{id}

Updates an existing coffee by its ID.

DELETE

/api/coffees/{id}

Deletes a coffee by its ID.

Example JSON Body

Use this format for POST /api/coffees and PUT /api/coffees/{id} requests.

{
"name": "Yirgacheffe",
"origin": "Ethiopia",
"roast": "LIGHT",
"price": 22.50,
"tastingNotes": "Blueberry, lemon, floral"
}

Roast options are: LIGHT, MEDIUM, DARK.