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

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for Product entity providing business logic.
 */
@ApplicationScoped
public class ProductService {

    @Inject
    ProductRepository productRepository;

    @Inject
    ProductMapper productMapper;

    @Inject
    AuditService auditService;

    /**
     * Get all products.
     *
     * @return list of all products
     */
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get all products with pagination.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of products
     */
    public List<ProductDTO> getAllProductsPaginated(int page, int size) {
        return productRepository.findAllPaginated(page, size).list().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Get the total count of products.
     *
     * @return the total count of products
     */
    public long countAllProducts() {
        return productRepository.countAll();
    }

    /**
     * Get product by ID.
     *
     * @param id the product ID
     * @return the product DTO
     * @throws NotFoundException if product not found
     */
    public ProductDTO getProductById(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        // Log the read operation
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
        Product product = new Product();
        productMapper.fromCreateDTO(productCreateDTO, product);
        productRepository.persist(product);

        auditService.logCreate("Product", product.id.toString(),
                "Created new product: " + product.getName() + ", Price: " + product.getPrice());

        return productMapper.toDTO(product);
    }

    /**
     * Update an existing product.
     *
     * @param id the product ID
     * @param productUpdateDTO the product data to update
     * @return the updated product DTO
     * @throws NotFoundException if product not found
     */
    @Transactional
    public ProductDTO updateProduct(Long id, ProductUpdateDTO productUpdateDTO) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        String originalName = product.getName();
        BigDecimal originalPrice = product.getPrice();

        productMapper.fromUpdateDTO(productUpdateDTO, product);
        productRepository.persist(product);

        String details = "Updated product: " + originalName;
        if (productUpdateDTO.getName() != null && !productUpdateDTO.getName().equals(originalName)) {
            details += " - Name changed to: " + product.getName();
        }
        if (productUpdateDTO.getPrice() != null && !productUpdateDTO.getPrice().equals(originalPrice)) {
            details += " - Price changed from: " + originalPrice + " to: " + product.getPrice();
        }

        auditService.logUpdate("Product", id.toString(), details);

        return productMapper.toDTO(product);
    }

    /**
     * Delete a product.
     *
     * @param id the product ID
     * @throws NotFoundException if product not found
     */
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id);
        if (product == null) {
            throw new NotFoundException("Product with ID " + id + " not found");
        }

        // Store product details for audit log
        String productName = product.getName();

        productRepository.delete(product);

        // Log the delete operation
        auditService.logDelete("Product", id.toString(), "Deleted product: " + productName);
    }

    /**
     * Find products by category.
     *
     * @param category the category
     * @return list of products in the category
     */
    public List<ProductDTO> findByCategory(String category) {
        return productRepository.findByCategory(category).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find products by category with pagination.
     *
     * @param category the category
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of products in the category
     */
    public List<ProductDTO> findByCategoryPaginated(String category, int page, int size) {
        return productRepository.findByCategoryPaginated(category, page, size).list().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
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
     * Find products by name (partial match).
     *
     * @param name the name to search for
     * @return list of products matching the name
     */
    public List<ProductDTO> findByName(String name) {
        return productRepository.findByNameContaining(name).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());
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
     * Find products by price range.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @return list of products in the price range
     */
    public List<ProductDTO> findByPriceRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productRepository.findByPriceRange(minPrice, maxPrice).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find products by price range with pagination.
     *
     * @param minPrice the minimum price
     * @param maxPrice the maximum price
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of products in the price range
     */
    public List<ProductDTO> findByPriceRangePaginated(BigDecimal minPrice, BigDecimal maxPrice, int page, int size) {
        return productRepository.findByPriceRangePaginated(minPrice, maxPrice, page, size).list().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
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
     * Find available products.
     *
     * @return list of available products
     */
    public List<ProductDTO> findAvailableProducts() {
        return productRepository.findAvailable().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
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
                .collect(Collectors.toList());
    }

    /**
     * Count available products.
     *
     * @return count of available products
     */
    public long countAvailableProducts() {
        return productRepository.countAvailable();
    }

    /**
     * Find products by publisher.
     *
     * @param publisher the publisher
     * @return list of products from the publisher
     */
    public List<ProductDTO> findByPublisher(String publisher) {
        return productRepository.findByPublisher(publisher).stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Find products by publisher with pagination.
     *
     * @param publisher the publisher
     * @param page the page number (0-based)
     * @param size the page size
     * @return paginated list of products from the publisher
     */
    public List<ProductDTO> findByPublisherPaginated(String publisher, int page, int size) {
        return productRepository.findByPublisherPaginated(publisher, page, size).list().stream()
                .map(productMapper::toDTO)
                .collect(Collectors.toList());
    }

    /**
     * Count products by publisher.
     *
     * @param publisher the publisher
     * @return count of products from the publisher
     */
    public long countByPublisher(String publisher) {
        return productRepository.countByPublisher(publisher);
    }

}
