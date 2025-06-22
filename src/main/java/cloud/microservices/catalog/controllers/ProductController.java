package cloud.microservices.catalog.controllers;

import cloud.microservices.catalog.dtos.ProductCreateDTO;
import cloud.microservices.catalog.dtos.ProductDTO;
import cloud.microservices.catalog.dtos.ProductUpdateDTO;
import cloud.microservices.catalog.services.ProductService;
import cloud.microservices.catalog.utils.PaginationUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * REST controller for Product entity.
 */
@Path("/api/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Tag(name = "Product", description = "Product operations")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    /**
     * The Product service.
     */
    @Inject
    ProductService productService;

    /**
     * Gets all products with pagination support.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return the paginated products
     */
    @GET
    @PermitAll
    @Operation(summary = "Get all products", description = "Returns all products in the catalog with pagination support")
    @APIResponse(responseCode = "200", description = "Paginated list of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response getAllProducts(
            @Parameter(description = "Page number (0-based)") @QueryParam("page") Integer page,
            @Parameter(description = "Page size") @QueryParam("size") Integer size) {
        logger.info("Getting all products with pagination - page: {}, size: {}", page, size);

        try {
            int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
            int validPage = pageParams[0];
            int validSize = pageParams[1];

            logger.debug("Validated pagination parameters - page: {}, size: {}", validPage, validSize);

            List<ProductDTO> products = productService.getAllProductsPaginated(validPage, validSize);
            long totalCount = productService.countAllProducts();

            logger.info("Retrieved {} products out of {} total, {}", products.size(), totalCount, products);

            return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize);
        } catch (Exception e) {
            logger.error("Error retrieving all products", e);
            throw e;
        }
    }

    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
    @GET
    @Path("/{id}")
    @PermitAll
    @Operation(summary = "Get product by ID", description = "Returns a product by its ID")
    @APIResponse(responseCode = "200", description = "The product",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "404", description = "Product not found")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response getProductById(@PathParam("id") Long id) {
        logger.info("Getting product by ID: {}", id);

        try {
            ProductDTO product = productService.getProductById(id);
            logger.info("Retrieved product: {}, name: {}", id, product.getName());
            return Response.ok()
                    .entity(product)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .encoding("UTF-8")
                    .build();
        } catch (NotFoundException e) {
            logger.warn("Product not found with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error retrieving product with ID: {}", id, e);
            throw e;
        }
    }

    /**
     * Create product response.
     *
     * @param productCreateDTO the product create dto
     * @return the response
     */
    @POST
    @PermitAll
    @Operation(summary = "Create a new product", description = "Creates a new product in the catalog")
    @APIResponse(responseCode = "201", description = "Product created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "400", description = "Invalid input")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response createProduct(@Valid ProductCreateDTO productCreateDTO) {
        logger.info("Creating new product: {}", productCreateDTO.getName());
        logger.debug("Product details: category={}, price={}",
                productCreateDTO.getCategory(), productCreateDTO.getPrice());

        try {
            ProductDTO createdProduct = productService.createProduct(productCreateDTO);
            logger.info("Product created successfully with ID: {}", createdProduct.getId());

            return Response.created(URI.create("/api/products/" + createdProduct.getId()))
                    .entity(createdProduct)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .encoding("UTF-8")
                    .build();
        } catch (Exception e) {
            logger.error("Error creating product: {}", productCreateDTO.getName(), e);
            throw e;
        }
    }

    /**
     * Update product response.
     *
     * @param id               the id
     * @param productUpdateDTO the product update dto
     * @return the response
     */
    @PUT
    @Path("/{id}")
    @PermitAll
    @Operation(summary = "Update a product", description = "Updates an existing product")
    @APIResponse(responseCode = "200", description = "Product updated",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "404", description = "Product not found")
    @APIResponse(responseCode = "400", description = "Invalid input")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response updateProduct(@PathParam("id") Long id, @Valid ProductUpdateDTO productUpdateDTO) {
        logger.info("Updating product with ID: {}", id);
        logger.debug("Update details: {}", productUpdateDTO);

        try {
            ProductDTO updatedProduct = productService.updateProduct(id, productUpdateDTO);
            logger.info("Product updated successfully: ID={}, name={}", id, updatedProduct.getName());

            return Response.ok()
                    .entity(updatedProduct)
                    .header("Content-Type", MediaType.APPLICATION_JSON)
                    .encoding("UTF-8")
                    .build();
        } catch (NotFoundException e) {
            logger.warn("Product not found for update with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error updating product with ID: {}", id, e);
            throw e;
        }
    }

    /**
     * Delete product response.
     *
     * @param id the id
     * @return the response
     */
    @DELETE
    @Path("/{id}")
    @PermitAll
    @Operation(summary = "Delete a product", description = "Deletes a product from the catalog")
    @APIResponse(responseCode = "204", description = "Product deleted")
    @APIResponse(responseCode = "404", description = "Product not found")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response deleteProduct(@PathParam("id") Long id) {
        logger.info("Deleting product with ID: {}", id);

        try {
            productService.deleteProduct(id);
            logger.info("Product deleted successfully: ID={}", id);

            return Response.noContent().build();
        } catch (NotFoundException e) {
            logger.warn("Product not found for deletion with ID: {}", id);
            throw e;
        } catch (Exception e) {
            logger.error("Error deleting product with ID: {}", id, e);
            throw e;
        }
    }

    /**
     * Find products by category with pagination support.
     *
     * @param category the category
     * @param page     the page number (0-based)
     * @param size     the page size
     * @return the paginated response
     */
    @GET
    @Path("/category/{category}")
    @PermitAll
    @Operation(summary = "Find products by category", description = "Returns products in the specified category with pagination support")
    @APIResponse(responseCode = "200", description = "Paginated list of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response findByCategory(
            @PathParam("category") String category,
            @Parameter(description = "Page number (0-based)") @QueryParam("page") Integer page,
            @Parameter(description = "Page size") @QueryParam("size") Integer size) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByCategoryPaginated(category, validPage, validSize);
        long totalCount = productService.countByCategory(category);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize);
    }

    /**
     * Search products by name with pagination support.
     *
     * @param name the name to search for
     * @param page the page number (0-based)
     * @param size the page size
     * @return the paginated response
     */
    @GET
    @Path("/search")
    @PermitAll
    @Operation(summary = "Search products by name", description = "Returns products matching the name pattern with pagination support")
    @APIResponse(responseCode = "200", description = "Paginated list of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response searchByName(
            @QueryParam("name") String name,
            @Parameter(description = "Page number (0-based)") @QueryParam("page") Integer page,
            @Parameter(description = "Page size") @QueryParam("size") Integer size) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByNamePaginated(name, validPage, validSize);
        long totalCount = productService.countByName(name);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize);
    }

    /**
     * Find products by price range with pagination support.
     *
     * @param minPrice the min price
     * @param maxPrice the max price
     * @param page     the page number (0-based)
     * @param size     the page size
     * @return the paginated response
     */
    @GET
    @Path("/price")
    @PermitAll
    @Operation(summary = "Find products by price range", description = "Returns products within the price range with pagination support")
    @APIResponse(responseCode = "200", description = "Paginated list of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response findByPriceRange(
            @QueryParam("min") BigDecimal minPrice,
            @QueryParam("max") BigDecimal maxPrice,
            @Parameter(description = "Page number (0-based)") @QueryParam("page") Integer page,
            @Parameter(description = "Page size") @QueryParam("size") Integer size) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByPriceRangePaginated(minPrice, maxPrice, validPage, validSize);
        long totalCount = productService.countByPriceRange(minPrice, maxPrice);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize);
    }

    /**
     * Find available products with pagination support.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @return the paginated response
     */
    @GET
    @Path("/available")
    @PermitAll
    @Operation(summary = "Find available products", description = "Returns all available products with pagination support")
    @APIResponse(responseCode = "200", description = "Paginated list of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response findAvailableProducts(
            @Parameter(description = "Page number (0-based)") @QueryParam("page") Integer page,
            @Parameter(description = "Page size") @QueryParam("size") Integer size) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findAvailableProductsPaginated(validPage, validSize);
        long totalCount = productService.countAvailableProducts();

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize);
    }

}
