# Catalog Microservice

A microservice for managing a catalog of software products and services.

## Overview

The Catalog Microservice is part of a microservices-based application for selling software products and services. It provides CRUD operations and search functionality for the product catalog.

## Features

- Create, read, update, and delete software products
- Search products by various criteria (name, category, price range, publisher)
- Filter available products
- RESTful API with JSON responses
- Swagger UI for API documentation and testing
- PostgreSQL database for data persistence
- Validation for data integrity
- Standardized error responses

## Tech Stack

- Java 21
- Quarkus 3.18.3
- PostgreSQL
- Hibernate ORM with Panache
- JAX-RS for REST endpoints
- Swagger/OpenAPI for documentation
- Docker support

## API Endpoints

### Product Operations

- `GET /api/products` - Get all products
- `GET /api/products/{id}` - Get product by ID
- `POST /api/products` - Create a new product
- `PUT /api/products/{id}` - Update an existing product
- `DELETE /api/products/{id}` - Delete a product

### Search Operations

- `GET /api/products/category/{category}` - Find products by category
- `GET /api/products/search?name={name}` - Search products by name
- `GET /api/products/price?min={min}&max={max}` - Find products by price range
- `GET /api/products/available` - Find available products
- `GET /api/products/publisher/{publisher}` - Find products by publisher

## Database Schema

The main entity in this microservice is the `Product` which has the following attributes:

- `id` - Unique identifier
- `name` - Product name
- `description` - Product description
- `price` - Product price
- `category` - Product category
- `version` - Software version
- `releaseDate` - Release date
- `publisher` - Publisher name
- `features` - Product features
- `requirements` - System requirements
- `isAvailable` - Availability status

## Environment Variables

The application can be configured using the following environment variables:

- `DB_USERNAME` - Database username (default: postgres)
- `DB_PASSWORD` - Database password (default: postgres)
- `DB_HOST` - Database host (default: localhost)
- `DB_PORT` - Database port (default: 5432)
- `DB_NAME` - Database name (default: catalog)
- `HTTP_PORT` - HTTP port for the application (default: 8088)

## Running the application

### Development Mode

```shell script
./mvnw quarkus:dev
```

This will start the application in development mode with hot reload.

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at <http://localhost:8088/q/dev/>.

### Production Mode

```shell script
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Docker

```shell script
./mvnw package
docker build -f src/main/docker/Dockerfile.jvm -t catalog-microservice .
docker run -i --rm -p 8088:8088 catalog-microservice
```

## API Documentation

Swagger UI is available at: http://localhost:8088/swagger-ui/

OpenAPI specification is available at: http://localhost:8088/openapi

## Health Checks

- Liveness check: http://localhost:8088/health/live
- Readiness check: http://localhost:8088/health/ready

## Related Guides

- REST resources for Hibernate ORM with Panache ([guide](https://quarkus.io/guides/rest-data-panache))
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache))
- REST ([guide](https://quarkus.io/guides/rest))
- Hibernate Validator ([guide](https://quarkus.io/guides/validation))
- SmallRye Health ([guide](https://quarkus.io/guides/smallrye-health))
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource))
