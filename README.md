# 📚 Library Management System

A REST API for managing a library's books, authors, members, and borrow records — built with Spring Boot, secured with JWT authentication, and enforcing role-based access control for Admins and Members.

## ✨ Features

- 🔐 **JWT Authentication** — register and log in to receive a signed token
- 👥 **Role-based access control** — Admin and Member roles, enforced per endpoint and per HTTP method
- 📖 **Book management** — full CRUD, Admin-only writes, Admin/Member reads
- ✍️ **Author management** — full CRUD, Admin-only writes, Admin/Member reads
- 🧑‍🤝‍🧑 **Member management** — full CRUD, Admin-only
- 🔄 **Borrow record tracking** — create, update, and return borrowed books via a dedicated return endpoint
- ⚠️ **Centralized exception handling** — custom exceptions per domain, mapped to clean error responses
- ✅ **Request validation** — `@Valid` enforced on all write endpoints

## 🛠️ Tech Stack

| Layer | Technology |
|---|---|
| Language | Java 17 |
| Framework | Spring Boot 4.1.0 |
| Security | Spring Security, JWT (jjwt 0.12.5) |
| Persistence | Spring Data JPA, MySQL |
| Build tool | Maven |
| Boilerplate reduction | Lombok |

## 📂 Project Structure

Organized by feature (vertical slice) rather than by technical layer, so everything related to one domain lives together:

```
src/main/java/pk/edu/niit/library_management_system/
├── Author/            Controller, DTO, Entity, Mapper, Repository, Services
├── Book/              Controller, DTO, Entity, Mapper, Repository, Services
├── BorrowRecord/      Controller, DTO, Entity, Mapper, Repository, Services, Util
├── Member/            Controller, DTO, Entity, Mapper, Repository, Services
├── User/               Controller (Auth), DTO, Entity, Repository, Service (JWT, UserDetails)
├── Config/            SecurityConfig, JwtAuthenticationFilter
└── ExceptionHandler/  GlobalExceptionHandler + custom domain exceptions
```

## 🚀 Getting Started

### Prerequisites

- Java 17+
- Maven (or use the included `mvnw` wrapper)
- MySQL running locally

### Installation

1. **Clone the repo**
   ```bash
   git clone https://github.com/muqadasilyas/library-management-system.git
   cd library-management-system
   ```

2. **Create the database**
   ```sql
   CREATE DATABASE library_management_system;
   ```

3. **Configure credentials**

   Set these as environment variables rather than committing them in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/library_management_system
   spring.datasource.username=${DB_USERNAME}
   spring.datasource.password=${DB_PASSWORD}
   ```

4. **Run the application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The API starts at `http://localhost:8080`.

## 🔑 Authentication

1. Register a user: `POST /auth/register`
2. Log in: `POST /auth/login` → returns a JWT
3. Include the token on all subsequent requests:
   ```
   Authorization: Bearer <your-token>
   ```

## 📡 API Reference

### Auth — `/auth` (public)

| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | Log in and receive a JWT |

### Books — `/book` (GET: Admin/Member · writes: Admin)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/book` | List all books |
| GET | `/book/id/{id}` | Get a book by ID |
| POST | `/book` | Create a book |
| PUT | `/book/id/{id}` | Update a book |
| DELETE | `/book/id/{id}` | Delete a book |

### Authors — `/author` (GET: Admin/Member · writes: Admin)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/author` | List all authors |
| GET | `/author/id/{id}` | Get an author by ID |
| POST | `/author` | Create an author |
| PUT | `/author/id/{id}` | Update an author |
| DELETE | `/author/id/{id}` | Delete an author |

### Members — `/member` (Admin only)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/member` | List all members |
| GET | `/member/id/{id}` | Get a member by ID |
| POST | `/member` | Create a member |
| PUT | `/member/id/{id}` | Update a member |
| DELETE | `/member/id/{id}` | Delete a member |

### Borrow Records — `/borrowrecord` (Admin only)

| Method | Endpoint | Description |
|---|---|---|
| GET | `/borrowrecord` | List all borrow records |
| GET | `/borrowrecord/id/{id}` | Get a record by ID |
| POST | `/borrowrecord` | Create a borrow record |
| PUT | `/borrowrecord/id/{id}` | Update a record |
| PUT | `/borrowrecord/id/{id}/return` | Mark a book as returned |
| DELETE | `/borrowrecord/id/{id}` | Delete a record |


## 👤 Author

**Muqadas Ilyas**
Software Engineering student, NASTP Institute of Information Technology (NIIT), Lahore
- GitHub: [@muqadasilyas](https://github.com/muqadasilyas)
- Portfolio: [muqadas-ilyas-portfolio.vercel.app](https://muqadas-ilyas-portfolio.vercel.app)

## 📄 License

Not yet specified.
