package cloud.microservices.catalog.repositories;

import cloud.microservices.catalog.PostgresTestResource;
import cloud.microservices.catalog.entities.Product;
import io.quarkus.test.TestTransaction;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(PostgresTestResource.class)
class ProductRepositoryTest {

    @Inject
    ProductRepository productRepository;

    @Test
    @TestTransaction
    void findsProductsByCategoryNamePriceAndAvailability() {
        Product editor = new Product("Code Editor", "IDE", new BigDecimal("49.99"), "developer-tools", true);
        Product archived = new Product("Old IDE", "Legacy IDE", new BigDecimal("19.99"), "developer-tools", false);
        productRepository.persist(editor);
        productRepository.persist(archived);

        assertEquals(2, productRepository.countByCategory("developer-tools"));
        assertEquals(1, productRepository.countByNameContaining("code"));
        assertEquals(2, productRepository.countByPriceRange(new BigDecimal("10.00"), new BigDecimal("50.00")));
        assertEquals(1, productRepository.countAvailable());
    }
}
