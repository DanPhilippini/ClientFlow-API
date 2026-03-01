# 🚀 ClientFlow API

Backend REST API for client and task management built with **Spring Boot 3**, featuring JWT authentication, role-based authorization, pagination, validation, and OpenAPI documentation.

> Designed with clean architecture principles and production-ready best practices.

---

## 📌 Overview

ClientFlow API is a secure and scalable backend service that allows:

- User authentication with JWT
- Role-based access control (USER / ADMIN)
- Client and task management
- Pagination and sorting
- Standardized error handling
- Interactive API documentation (Swagger UI)

This project was built as a professional-grade backend portfolio project.

---

## 🏗 Architecture

The project follows a clean layered architecture:
controller → service → repository → database


---

## 🔐 Security

- JWT-based authentication
- Stateless session management
- Custom AuthenticationEntryPoint
- Role-based authorization
- Password encryption with BCrypt
- Structured error responses

### Authentication Flow

1. User logs in
2. API returns JWT access token
3. Token must be sent in header:
   Authorization: Bearer <token>


---

## 📖 API Documentation

Swagger UI available at:
http://localhost:8080/swagger-ui.html


Features:
- Full endpoint documentation
- Request/response examples
- JWT Authorize button
- Error response schema

---

## 🛠 Technologies Used

- Java 21
- Spring Boot 3.4.x
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (jjwt 0.11.5)
- OpenAPI / Swagger (springdoc)
- Maven
- Docker & Docker Compose
- Lombok

---

## ⚙️ Running the Project

### 1 - Clone the repository

```bash
git clone https://github.com/DanPhilippini/ClientFlow-API.git
cd ClientFlow-API
```

### 2 - Run with Docker (Recommended)

```bash
docker-compose up --build
```

---

## 📦 Environment Variables

Example configuration:

SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/clientflow

SPRING_DATASOURCE_USERNAME=postgres

SPRING_DATASOURCE_PASSWORD=postgres

JWT_SECRET=your_secret_key

---

🧪 Testing

Run tests with:

mvn test

📄 Example Response

✅ Success

{
"id": 1,
"name": "Client A",
"email": "client@email.com"
}

❌ Error

{
"timestamp": "2026-02-28T10:15:30",
"status": 401,
"error": "Unauthorized",
"message": "Invalid or expired token",
"path": "/api/v1/clients"
}

---

## 🎯 Key Features Implemented

✔ Layered architecture

✔ JWT authentication

✔ Role-based authorization

✔ DTO validation

✔ Pagination & sorting

✔ Global exception handling

✔ Swagger documentation

✔ Dockerized environment

---

## 🚀 Possible Future Improvements


CI/CD pipeline (GitHub Actions)

Token blacklist / rotation

Rate limiting

Monitoring with Spring Actuator

---

## 👨‍💻 Author

Daniel Philippini

Backend Developer – Java & Spring Boot

GitHub:
https://github.com/DanPhilippini