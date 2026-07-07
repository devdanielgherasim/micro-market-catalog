package cloud.microservices.catalog.controllers;

import cloud.microservices.catalog.services.AuditService;
import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@QuarkusTest
class ProductControllerTest {

    @InjectMock
    AuditService auditService;

    @Test
    void createsReadsUpdatesAndDeletesProduct() {
        Integer id = given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "Trace Toolkit",
                          "description": "Observability bundle",
                          "price": 79.99,
                          "category": "observability",
                          "available": true
                        }
                        """)
                .when()
                .post("/api/products")
                .then()
                .statusCode(201)
                .body("id", notNullValue())
                .body("name", equalTo("Trace Toolkit"))
                .extract()
                .path("id");

        given()
                .when()
                .get("/api/products/{id}", id)
                .then()
                .statusCode(200)
                .body("category", equalTo("observability"));

        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "price": 89.99
                        }
                        """)
                .when()
                .put("/api/products/{id}", id)
                .then()
                .statusCode(200)
                .body("price", equalTo(89.99F));

        given()
                .when()
                .delete("/api/products/{id}", id)
                .then()
                .statusCode(204);

        given()
                .when()
                .get("/api/products/{id}", id)
                .then()
                .statusCode(404);
    }

    @Test
    void rejectsInvalidProductPayload() {
        given()
                .contentType(ContentType.JSON)
                .body("""
                        {
                          "name": "",
                          "price": -1,
                          "category": ""
                        }
                        """)
                .when()
                .post("/api/products")
                .then()
                .statusCode(400);
    }
}
