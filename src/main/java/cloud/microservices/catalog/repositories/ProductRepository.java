package cloud.microservices.catalog.repositories;

import cloud.microservices.catalog.entities.Product;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import jakarta.enterprise.context.ApplicationScoped;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository for Product entity providing database operations.
 * Includes methods for both standard and paginated queries.
 */
@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

    /**
     * Get a paginated query for all products.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return a paginated query for all products
     */
    public PanacheQuery<Product> findAllPaginated(int page, int size) {
        return findAll().page(Page.of(page, size));
    }

    /**
     * Count all products.
     *
     * @return the total count of products
     */
    public long countAll() {
        return count();
    }

    /**
     * Find products by category.
     *
     * @param category the category to search for
     * @return list of products in the specified category
     */
    public List<Product> findByCategory(String category) {
        return list("category", category);
    }

    /**
     * Find products by category with pagination.
     *
     * @param category the category to search for
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated query of products in the specified category
     */
    public PanacheQuery<Product> findByCategoryPaginated(String category, int page, int size) {
        return find("category", category).page(Page.of(page, size));
    }

    /**
     * Count products by category.
     *
     * @param category the category to search for
     * @return count of products in the specified category
     */
    public long countByCategory(String category) {
        return count("category", category);
    }

    /**
     * Find products by name (case insensitive, partial match).
     *
     * @param name the name to search for
     * @return list of products matching the name pattern
     */
    public List<Product> findByNameContaining(String name) {
        return list("LOWER(name) LIKE LOWER(?1)", "%" + name + "%");
    }

    /**
     * Find products by name with pagination (case insensitive, partial match).
     *
     * @param name the name to search for
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated query of products matching the name pattern
     */
    public PanacheQuery<Product> findByNameContainingPaginated(String name, int page, int size) {
        return find("LOWER(name) LIKE LOWER(?1)", "%" + name + "%").page(Page.of(page, size));
    }

    /**
     * Count products by name (case insensitive, partial match).
     *
     * @param name the name to search for
     * @return count of products matching the name pattern
     */
    public long countByNameContaining(String name) {
        return count("LOWER(name) LIKE LOWER(?1)", "%" + name + "%");
    }

    /**
     * Find products by price range.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return list of products within the price range
     */
    public List<Product> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return list("price >= ?1 AND price <= ?2", minPrice, maxPrice);
    }

    /**
     * Find products by price range with pagination.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated query of products within the price range
     */
    public PanacheQuery<Product> findByPriceRangePaginated(BigDecimal minPrice, BigDecimal maxPrice, int page, int size) {
        return find("price >= ?1 AND price <= ?2", minPrice, maxPrice).page(Page.of(page, size));
    }

    /**
     * Count products by price range.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return count of products within the price range
     */
    public long countByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return count("price >= ?1 AND price <= ?2", minPrice, maxPrice);
    }

    /**
     * Find available products.
     *
     * @return list of available products
     */
    public List<Product> findAvailable() {
        return list("isAvailable", true);
    }

    /**
     * Find available products with pagination.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated query of available products
     */
    public PanacheQuery<Product> findAvailablePaginated(int page, int size) {
        return find("isAvailable", true).page(Page.of(page, size));
    }

    /**
     * Count available products.
     *
     * @return count of available products
     */
    public long countAvailable() {
        return count("isAvailable", true);
    }

}
