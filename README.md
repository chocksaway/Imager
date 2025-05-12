# Imager

Imager allows you to manage albums and images.

## Features

- Create and manage galleries
- Add and organize pictures
- Search galleries by username or picture ID

## Prerequisites

- Java 21 or higher
- Maven 3.8+
- A database (e.g., MySQL, PostgreSQL) configured in `application.properties`

## Getting Started

1. Clone the repository:
   ```bash
   git clone https://github.com/chocksaway/imager.git
   cd imager
```

2. Build the project:
```
mvn clean install
Run the application:
```
3. Run Imager
```
mvn spring-boot:run
The application will start on http://localhost:8080.
```

3.5 Log with milesd milesd

4. API Endpoints

GET /galleries - Retrieve all galleries
POST /galleries - Create a new gallery
GET /galleries/{id} - Retrieve a specific gallery by ID
GET /galleries/username/{username} - Retrieve galleries by username
GET /galleries/picture/{pictureId} - Retrieve galleries by picture ID