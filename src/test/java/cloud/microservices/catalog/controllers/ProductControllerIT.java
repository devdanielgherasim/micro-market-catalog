package cloud.microservices.catalog.controllers;

import cloud.microservices.catalog.PostgresTestResource;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@QuarkusIntegrationTest
@QuarkusTestResource(value = PostgresTestResource.class, restrictToAnnotatedClass = true)
class ProductControllerIT {

    @Test
    void packagedApplicationServesReadinessProbe() {
        given()
                .when()
                .get("/health/ready")
                .then()
                .statusCode(200);
    }
}
