# Java Spring Authentication JWT

A robust authentication and authorization service template built with **Java 25** and **Spring Boot 4**, featuring **JWT (JSON Web Token)** security and **PostgreSQL** database integration.

## Features

- **User Registration**: Create new user accounts.
- **Authentication**: JWT-based login system.
- **Authorization**: Role-based access control (Admin/User).
- **Secure Endpoints**: Protected resources requiring valid JWT tokens.
- **Persistence**: User data storage using PostgreSQL.
- **Environment Configuration**: Secure credentials management using `.env` files.

## Tech Stack

- **Java**: 25
- **Spring Boot**: 4.0.2
- **Database**: PostgreSQL
- **Security**: Spring Security, JWT (JJWT 0.13.0)
- **Utilities**: Lombok, MapStruct, Apache Commons

## Prerequisites

- JDK 25
- Maven
- PostgreSQL Database
- Git

## Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/yourusername/java-spring-authentication-jwt.git
   cd java-spring-authentication-jwt
   ```

2. **Database Setup**
   Ensure you have a PostgreSQL database running. You can verify the default schema in `src/main/resources/sql/schema.sql`.

3. **Configuration**
   The application relies on environment variables.

   Copy `.env.example` (if available) or create a `.env` file in the root directory:
   ```properties
   # Database Configuration
   DB_URL=jdbc:postgresql://localhost:5432/your_database
   DB_USERNAME=your_username
   DB_PASSWORD=your_password

   # JWT Configuration
   # Generate a strong secret: openssl rand -hex 32
   JWT_SECRET=your_256_bit_secret_key_here
   JWT_EXPIRATION=3600000
   ```

4. **Build the Application**
   ```bash
   ./mvnw clean install
   ```

5. **Run the Application**
   ```bash
   ./mvnw spring-boot:run
   ```
   The application will start on port `8080` (default).

## API Endpoints

### Authentication (`/auth`)

| Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/register` | Register a new user | Public |
| `POST` | `/auth/login` | Login and receive JWT token | Public |
| `GET` | `/auth/secure` | Test secure access validity | Authenticated |

### Users (`/users`)

| Method | Endpoint | Description | Access | 
| :--- | :--- | :--- | :--- |
| `GET` | `/users/all` | Get all users | Authenticated |
| `GET` | `/users/user/{id}` | Get specific user details | Authenticated |
| `GET` | `/users/greet` | Greeting demo | Authenticated |

> **Note**: For authenticated endpoints, you must include the JWT token in the `Authorization` header: 
> `Authorization: Bearer <your_token>`

## Default Data

The application loads sample data on startup (via `src/main/resources/sql/data.sql`).

| Username | Role |
| :--- | :--- |
| `pedro85` | `ROLE_ADMIN` |
| `alice` | `ROLE_USER` |
| `bobsmith` | `ROLE_USER` |
| `carollee` | `ROLE_USER` |
| `davem` | `ROLE_ADMIN` |

> Passwords in the database are hashed with BCrypt.

## Security Configuration

### Environment Variables Setup

This application uses environment variables for sensitive configuration. **Never commit secrets to version control.**

1. Copy `.env.example` to `.env`:
   ```bash
   cp .env.example .env
   ```

2. Update `.env` with your secure values (as shown in Installation).

3. **Production Deployment**: Use your platform's environment variable management:
   - Heroku: `heroku config:set JWT_SECRET=xxx`
   - Docker: Use `docker run -e JWT_SECRET=xxx`
   - Kubernetes: Use Secrets
   - AWS: Use Systems Manager Parameter Store or Secrets Manager

### Important Security Notes

- Default passwords in `data.sql` are BCrypt-hashed and for **DEVELOPMENT ONLY**
- Always use strong, unique secrets in production
- Rotate JWT secrets regularly
- Use HTTPS in production
- Never expose `.env` files
