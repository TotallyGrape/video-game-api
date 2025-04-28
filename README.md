# video-game-api

This is an API built with Spring Boot and MongoDB for managing user accounts, sessions, and video game-related data.

It includes:

- Secure session-based authentication
- Role-based access control (Admin and regular users)
- Full CRUD for games and reviews
- Protected routes based on user roles
- MongoDB database integration

## Features

- User registration and login
- Add, edit, delete, and fetch video games
- Add and view reviews for games
- Admin dashboard for managing games and reviews
- Role-based permissions (Admin users can delete content)

## Tech Stack

- Java 17+
- Spring Boot
- Spring Security
- MongoDB Atlas
- Session Authentication (no JWT)
- HTML + JavaScript Frontend

## Setup

1. Clone the repository:

    ```bash
    git clone https://github.com/your-username/video-game-api.git
    cd video-game-api
    ```

2. Create a `.env` file:

    ```bash
    cp .env.example .env
    ```

   Fill in your real MongoDB URI inside `.env`.

3. Build and run the project:

    ```bash
    ./mvnw spring-boot:run
    ```

4. Open the app:

    ```
    http://localhost:8080
    ```

## Environment Variables

| Variable    | Description                       |
|-------------|-----------------------------------|
| `MONGO_URI` | Your MongoDB connection string    |

Example `.env.example`:

```env
MONGO_URI=mongodb+srv://user:pass@video-game-api.z7aj9bv.mongodb.net/video-game-api-db
```
