# Products Service — Spring Boot + Docker

A layered Spring Boot REST API with JSON file-based serialization for full CRUD on products.

---

## Project Structure

```
src/main/java/com/example/products/
├── ProductsApplication.java          ← Entry point
├── entities/
│   └── Product.java                  ← Domain model
├── repositories/
│   ├── ProductRepository.java        ← Repository interface
│   └── JsonProductRepository.java    ← JSON file persistence impl
├── services/
│   ├── ProductService.java           ← Service interface
│   ├── ProductServiceImpl.java       ← Business logic
│   └── ProductNotFoundException.java ← Domain exception
└── controllers/
    ├── ProductsController.java       ← REST endpoints
    └── GlobalExceptionHandler.java   ← Centralised error handling
```

---

## Run with Docker

```bash
# Build & start
docker-compose up --build

# Run in background
docker-compose up -d --build

# Stop
docker-compose down
```

The API will be available at `http://localhost:8080`.

---

## Run Locally (without Docker)

```bash
mvn spring-boot:run
```

---

## API Endpoints

### Get all products
```
GET /api/products
```

### Filter by category
```
GET /api/products?category=Electronics
```

### Filter active products
```
GET /api/products?active=true
```

### Get single product
```
GET /api/products/{id}
```

### Create product
```
POST /api/products
Content-Type: application/json

{
  "name": "MacBook Pro",
  "description": "Apple M3 laptop",
  "price": 1999.99,
  "category": "Electronics",
  "stockQuantity": 50
}
```

### Update product
```
PUT /api/products/{id}
Content-Type: application/json

{
  "name": "MacBook Pro M3 Max",
  "description": "Updated description",
  "price": 2499.99,
  "category": "Electronics",
  "stockQuantity": 30,
  "active": true
}
```

### Delete product
```
DELETE /api/products/{id}
```

### Count products
```
GET /api/products/count
```

---

## Data Persistence

Products are serialized to JSON at `data/products.json` (mounted as a Docker volume for persistence across container restarts).

---

## Example cURL

```bash
# Create
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Keyboard","price":79.99,"category":"Accessories","stockQuantity":100}'

# List all
curl http://localhost:8080/api/products

# Delete
curl -X DELETE http://localhost:8080/api/products/{id}
```
