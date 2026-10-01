# Spring Security JWT Auth

A Spring Boot REST API with JWT-based authentication and role-based authorization using Spring Security.

## Features

- User signup and login
- Stateless JWT authentication
- BCrypt password hashing
- Role-based access control
- Protected CRUD endpoints for posts

## Tech Stack

Java 17+, Spring Boot, Spring Security, JWT, Spring Data JPA, Maven, Lombok

## Getting Started

```bash
git clone https://github.com/yourusername/spring-security-jwt-auth.git
cd spring-security-jwt-auth
```

Create `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

jwt.secretKey=YOUR_LONG_RANDOM_SECRET_KEY
```

Run the app:

```bash
./mvnw spring-boot:run
```

The API runs at `http://localhost:8080`.

## API Endpoints

| Method | Endpoint | Access |
|--------|----------|--------|
| POST | `/auth/signup` | Public |
| POST | `/auth/login` | Public |
| GET | `/posts` | Authenticated |
| POST | `/posts` | Authenticated |
| DELETE | `/posts/{id}` | Admin only |

Send the token from login in the header: `Authorization: Bearer <token>`

## License

MIT
