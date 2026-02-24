# 🚀 ClientFlow API

ClientFlow API is a production-ready Spring Boot REST API designed for secure multi-tenant client and task management.

It provides JWT authentication, role-based and owner-based authorization, pagination, validation, Docker support, and CI/CD integration.

---

## 📌 Overview

ClientFlow is built to simulate a real SaaS backend used by freelancers and small businesses to manage:

- Users
- Clients
- Tasks

Each user can only access their own data (owner-based authorization), making the system secure and multi-tenant ready.

---

## 🧠 Key Features

- 🔐 JWT Stateless Authentication
- 👥 Role-Based Authorization (ADMIN / USER)
- 🏢 Owner-Based Data Isolation (Multi-tenant)
- 📄 Pagination & Sorting
- ✅ Request Validation
- 🐳 Dockerized Setup
- 🧪 Unit & Integration Tests
- ⚙ CI/CD with GitHub Actions
- 📘 Swagger API Documentation

---

## 🏗 Architecture

The project follows a layered architecture:

controller → service → repository → database

It also implements:

- DTO Pattern
- Global Exception Handling
- Clean separation of concerns
- Secure password encryption (BCrypt)

---

## 🛠 Tech Stack

- Java 17+
- Spring Boot 3
- Spring Security
- JWT
- PostgreSQL
- Docker
- JUnit 5
- Mockito
- GitHub Actions

---

## 🔐 Authentication Flow

1. User registers
2. User logs in
3. API returns JWT token
4. Token must be sent in headers:

Authorization: Bearer <token>

All protected endpoints require authentication.

---

## 📘 API Documentation

Swagger UI available at:

/swagger-ui.html

---

## 🐳 Running with Docker

```bash
docker compose up --build
