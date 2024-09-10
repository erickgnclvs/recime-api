# ReciMe API

## Overview

The ReciMe API is a Spring Boot application that provides endpoints for managing recipes. This API allows you to fetch trending recipes, filter recipes by difficulty, and handle various scenarios for invalid or missing parameters.

## Endpoints

### 1. Get Trending Recipes

**Endpoint:** `GET /api/recipes/trending`

**Description:** Retrieves a list of trending recipes, ordered by score.

**Response:** 
- `200 OK` with a list of `RecipeDTO` objects.

### 2. Get Filtered Recipes

**Endpoint:** `GET /api/recipes/trending/filter`

**Description:** Retrieves a list of recipes filtered by difficulty.

**Query Parameters:**
- `difficulty`: The difficulty level to filter by. Valid values are `EASY`, `MEDIUM`, and `HARD`.

**Responses:**
- `200 OK` with a list of `RecipeDTO` objects.
- `400 Bad Request` if an invalid difficulty is provided or if no difficulty is specified.

## Running the Application

### Prerequisites

- Java 17 or higher
- Maven 3.8.1 or higher

### Steps

1. **Clone the Repository**

   ```bash
   git clone https://github.com/erickgnclvs/recime-api.git
   cd recime-api
   ```

2. **Build the Project**

   ```bash
   mvn clean install
   ```

3. **Run the Application**

   ```bash
   mvn spring-boot:run
   ```

   By default, the application will run on `http://localhost:8080`.

## Testing with Postman

1. **Import the Postman Collection**

    - Open Postman.
    - Click on the **Import** button (top left).
    - Choose **File** and select the `recime_api_postman_collection.json` file located in the root of the repository.
    - Click **Import** to load the collection into Postman.

2. **Run Tests**

    - Open the imported collection in Postman.
    - Click on each request and hit **Send** to test the API endpoints.

## Running Tests

To run unit tests for the service and controller:

```bash
mvn test
```