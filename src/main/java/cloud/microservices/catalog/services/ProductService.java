package cloud.microservices.catalog.services;

import cloud.microservices.catalog.dtos.ProductCreateDTO;
import cloud.microservices.catalog.dtos.ProductDTO;
import cloud.microservices.catalog.dtos.ProductUpdateDTO;
import cloud.microservices.catalog.entities.Product;
import cloud.microservices.catalog.mappers.ProductMapper;
import cloud.microservices.catalog.repositories.ProductRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.List;

/**
 * Service class for Product entity providing business logic.
 */
@ApplicationScoped
public class ProductService {

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    @Inject
    AuditService auditService;

    /**
     * Get all products with pagination.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of products
     */
    public List<ProductDTO> getAllProductsPaginated(int page, int size) {
        logger.info("Service: Getting all products with pagination - page: {}, size: {}", page, size);

        try {
            logger.debug("Service: Retrieving paginated products from repository");
            List<ProductDTO> products = productRepository.findAllPaginated(page, size).list().stream()
                    .map(productMapper::toDTO)
                    .toList();

            logger.info("Service: Retrieved {} products for page {} with size {}", products.size(), page, size);

            return products;
        } catch (Exception e) {
            logger.error("Service: Error retrieving paginated products - page: {}, size: {}", page, size, e);
            throw e;
        }
    }

    /**
     * Get the total count of products.
     *
     * @return the total count of products
     */
    public long countAllProducts() {
        logger.debug("Service: Counting all products");

        try {
            long count = productRepository.countAll();
            logger.debug("Service: Total product count: {}", count);
            return count;
        } catch (Exception e) {
            logger.error("Service: Error counting all products", e);
            throw e;
        }
    }

    /**
     * Get product by ID.
     *
     * @param id the product ID
     * @return the product DTO
     * @throws NotFoundException if product not found
     */
    public ProductDTO getProductById(Long id) {
        logger.debug("Service: Getting product by ID: {}", id);

        Product product = productRepository.findById(id);
        if (product == null) {
            logger.warn("Service: Product with ID {} not found", id);
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        logger.debug("Service: Found product: {}, name: {}, price: {}",
                id, product.getName(), product.getPrice());

        auditService.logRead("Product", id.toString(), "Viewed product: " + product.getName());

        return productMapper.toDTO(product);
    }

    /**
     * Create a new product.
     *
     * @param productCreateDTO the product data
     * @return the created product DTO
     */
    @Transactional
    public ProductDTO createProduct(ProductCreateDTO productCreateDTO) {
        logger.info("Service: Creating new product: {}", productCreateDTO.getName());
        logger.debug("Service: Product details to create: category={}, price={}",
                productCreateDTO.getCategory(), productCreateDTO.getPrice());

        try {
            Product product = new Product();
            productMapper.fromCreateDTO(productCreateDTO, product);

            logger.debug("Service: Persisting product to database");
            productRepository.persist(product);
            logger.info("Service: Product persisted successfully with ID: {}", product.id);

            auditService.logCreate("Product", product.id.toString(),
                    "Created new product: " + product.getName() + ", Price: " + product.getPrice());

            return productMapper.toDTO(product);
        } catch (Exception e) {
            logger.error("Service: Error creating product: {}", productCreateDTO.getName(), e);
            throw e;
        }
    }

    /**
     * Update an existing product.
     *
     * @param id               the product ID
     * @param productUpdateDTO the product data to update
     * @return the updated product DTO
     * @throws NotFoundException if product not found
     */
    @Transactional
    public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        logger.info("Service: Updating product with ID: {}", id);
        logger.debug("Service: Update details: {}", productUpdateDTO);

        try {
            Product product = productRepository.findById(id);
            if (product == null) {
                logger.warn("Service: Product with ID {} not found for update", id);
                throw new NotFoundException("Product with ID " + id + " not found");
            }

            logger.debug("Service: Found product to update: {}, current name: {}, current price: {}",
                    id, product.getName(), product.getPrice());

            String originalName = product.getName();
            BigDecimal originalPrice = product.getPrice();

            productMapper.fromUpdateDTO(productUpdateDTO, product);

            logger.debug("Service: Persisting updated product to database");
            productRepository.persist(product);
            logger.info("Service: Product updated successfully: ID={}", id);

            String details = "Updated product: " + originalName;
            if (productUpdateDTO.getName() != null && !productUpdateDTO.getName().equals(originalName)) {
                details += " - Name changed to: " + product.getName();
                logger.info("Service: Product name changed from '{}' to '{}'", originalName, product.getName());
            }
            if (productUpdateDTO.getPrice() != null && !productUpdateDTO.getPrice().equals(originalPrice)) {
                details += " - Price changed from: " + originalPrice + " to: " + product.getPrice();
                logger.info("Service: Product price changed from {} to {}", originalPrice, product.getPrice());
            }

            auditService.logUpdate("Product", id.toString(), details);

            return productMapper.toDTO(product);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Service: Error updating product with ID: {}", id, e);
            throw e;
        }
    }

    /**
     * Delete a product.
     *
     * @param id the product ID
     * @throws NotFoundException if product not found
     */
    @Transactional
    public void deleteProduct(Long id) {
        logger.info("Service: Deleting product with ID: {}", id);

        try {
            Product product = productRepository.findById(id);
            if (product == null) {
                logger.warn("Service: Product with ID {} not found for deletion", id);
                throw new NotFoundException("Product with ID " + id + " not found");
            }

            logger.debug("Service: Found product to delete: {}, name: {}, price: {}",
                    id, product.getName(), product.getPrice());

            String productName = product.getName();

            logger.debug("Service: Deleting product from database");
            productRepository.delete(product);
            logger.info("Service: Product deleted successfully: ID={}, name={}", id, productName);

            auditService.logDelete("Product", id.toString(), "Deleted product: " + productName);
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            logger.error("Service: Error deleting product with ID: {}", id, e);
            throw e;
        }
    }

    /**
     * Find products by category with pagination.
     *
     * @param category the category
     * @param page     the page number (0-based)
     * @param size     the page size
     * @return paginated list of products in the category
     */
    public List<ProductDTO> findByCategoryPaginated(String category, int page, int size) {
        return productRepository.findByCategoryPaginated(category, page, size).list().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    /**
     * Count products by category.
     *
     * @param category the category
     * @return count of products in the category
     */
    public long countByCategory(String category) {
        return productRepository.countByCategory(category);
    }


    /**
     * Find products by name with pagination (partial match).
     *
     * @param name the name to search for
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of products matching the name
     */
    public List<ProductDTO> findByNamePaginated(String name, int page, int size) {
        return productRepository.findByNameContainingPaginated(name, page, size).list().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    /**
     * Count products by name (partial match).
     *
     * @param name the name to search for
     * @return count of products matching the name
     */
    public long countByName(String name) {
        return productRepository.countByNameContaining(name);
    }

    /**
     * Find products by price range with pagination.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param page     the page number (0-based)
     * @param size     the page size
     * @return paginated list of products in the price range
     */
    public List<ProductDTO> findByPriceRangePaginated(BigDecimal minPrice, BigDecimal maxPrice, int page, int size) {
        return productRepository.findByPriceRangePaginated(minPrice, maxPrice, page, size).list().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    /**
     * Count products by price range.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return count of products in the price range
     */
    public long countByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.countByPriceRange(minPrice, maxPrice);
    }


    /**
     * Find available products with pagination.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of available products
     */
    public List<ProductDTO> findAvailableProductsPaginated(int page, int size) {
        return productRepository.findAvailablePaginated(page, size).list().stream()
                .map(productMapper::toDTO)
                .toList();
    }

    /**
     * Count available products.
     *
     * @return count of available products
     */
    public long countAvailableProducts() {
        return productRepository.countAvailable();
    }


}
