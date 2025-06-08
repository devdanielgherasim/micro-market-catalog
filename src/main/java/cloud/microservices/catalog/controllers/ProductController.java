package cloud.microservices.catalog.controllers;

import cloud.microservices.catalog.dtos.ProductCreateDTO;
import cloud.microservices.catalog.dtos.ProductDTO;
import cloud.microservices.catalog.dtos.ProductUpdateDTO;
import cloud.microservices.catalog.services.ProductService;
import cloud.microservices.catalog.utils.PaginationUtil;
import jakarta.annotation.security.PermitAll;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

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
     * @param uriInfo the URI info for building pagination links
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
            @Parameter(description = "Page number (0-based)", required = false) @QueryParam("page") Integer page,
            @Parameter(description = "Page size", required = false) @QueryParam("size") Integer size,
            @Context UriInfo uriInfo) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.getAllProductsPaginated(validPage, validSize);
        long totalCount = productService.countAllProducts();

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize, uriInfo);
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
        ProductDTO product = productService.getProductById(id);
        return Response.ok(product).build();
    }

    /**
     * Create product response.
     *
     * @param productCreateDTO the product create dto
     * @return the response
     */
    @POST
    @RolesAllowed("admin")
    @Operation(summary = "Create a new product", description = "Creates a new product in the catalog")
    @APIResponse(responseCode = "201", description = "Product created",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "400", description = "Invalid input")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response createProduct(@Valid ProductCreateDTO productCreateDTO) {
        ProductDTO createdProduct = productService.createProduct(productCreateDTO);
        return Response.created(URI.create("/api/products/" + createdProduct.getId()))
                .entity(createdProduct)
                .build();
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
    @RolesAllowed("admin")
    @Operation(summary = "Update a product", description = "Updates an existing product")
    @APIResponse(responseCode = "200", description = "Product updated",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "404", description = "Product not found")
    @APIResponse(responseCode = "400", description = "Invalid input")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response updateProduct(@PathParam("id") Long id, @Valid ProductUpdateDTO productUpdateDTO) {
        ProductDTO updatedProduct = productService.updateProduct(id, productUpdateDTO);
        return Response.ok(updatedProduct).build();
    }

    /**
     * Delete product response.
     *
     * @param id the id
     * @return the response
     */
    @DELETE
    @Path("/{id}")
    @RolesAllowed("admin")
    @Operation(summary = "Delete a product", description = "Deletes a product from the catalog")
    @APIResponse(responseCode = "204", description = "Product deleted")
    @APIResponse(responseCode = "404", description = "Product not found")
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response deleteProduct(@PathParam("id") Long id) {
        productService.deleteProduct(id);
        return Response.noContent().build();
    }

    /**
     * Find products by category with pagination support.
     *
     * @param category the category
     * @param page the page number (0-based)
     * @param size the page size
     * @param uriInfo the URI info for building pagination links
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
            @Parameter(description = "Page number (0-based)", required = false) @QueryParam("page") Integer page,
            @Parameter(description = "Page size", required = false) @QueryParam("size") Integer size,
            @Context UriInfo uriInfo) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByCategoryPaginated(category, validPage, validSize);
        long totalCount = productService.countByCategory(category);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize, uriInfo);
    }

    /**
     * Search products by name with pagination support.
     *
     * @param name the name to search for
     * @param page the page number (0-based)
     * @param size the page size
     * @param uriInfo the URI info for building pagination links
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
            @Parameter(description = "Page number (0-based)", required = false) @QueryParam("page") Integer page,
            @Parameter(description = "Page size", required = false) @QueryParam("size") Integer size,
            @Context UriInfo uriInfo) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByNamePaginated(name, validPage, validSize);
        long totalCount = productService.countByName(name);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize, uriInfo);
    }

    /**
     * Find products by price range with pagination support.
     *
     * @param minPrice the min price
     * @param maxPrice the max price
     * @param page the page number (0-based)
     * @param size the page size
     * @param uriInfo the URI info for building pagination links
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
            @Parameter(description = "Page number (0-based)", required = false) @QueryParam("page") Integer page,
            @Parameter(description = "Page size", required = false) @QueryParam("size") Integer size,
            @Context UriInfo uriInfo) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByPriceRangePaginated(minPrice, maxPrice, validPage, validSize);
        long totalCount = productService.countByPriceRange(minPrice, maxPrice);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize, uriInfo);
    }

    /**
     * Find available products with pagination support.
     *
     * @param page the page number (0-based)
     * @param size the page size
     * @param uriInfo the URI info for building pagination links
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
            @Parameter(description = "Page number (0-based)", required = false) @QueryParam("page") Integer page,
            @Parameter(description = "Page size", required = false) @QueryParam("size") Integer size,
            @Context UriInfo uriInfo) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findAvailableProductsPaginated(validPage, validSize);
        long totalCount = productService.countAvailableProducts();

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize, uriInfo);
    }

    /**
     * Find products by publisher with pagination support.
     *
     * @param publisher the publisher
     * @param page the page number (0-based)
     * @param size the page size
     * @param uriInfo the URI info for building pagination links
     * @return the paginated response
     */
    @GET
    @Path("/publisher/{publisher}")
    @PermitAll
    @Operation(summary = "Find products by publisher", description = "Returns products from the specified publisher with pagination support")
    @APIResponse(responseCode = "200", description = "Paginated list of products",
            content = @Content(mediaType = MediaType.APPLICATION_JSON,
                    schema = @Schema(implementation = ProductDTO.class)))
    @APIResponse(responseCode = "401", description = "Unauthorized")
    @APIResponse(responseCode = "403", description = "Forbidden")
    public Response findByPublisher(
            @PathParam("publisher") String publisher,
            @Parameter(description = "Page number (0-based)", required = false) @QueryParam("page") Integer page,
            @Parameter(description = "Page size", required = false) @QueryParam("size") Integer size,
            @Context UriInfo uriInfo) {

        int[] pageParams = PaginationUtil.validatePaginationParams(page, size);
        int validPage = pageParams[0];
        int validSize = pageParams[1];

        List<ProductDTO> products = productService.findByPublisherPaginated(publisher, validPage, validSize);
        long totalCount = productService.countByPublisher(publisher);

        return PaginationUtil.createPaginatedResponse(products, totalCount, validPage, validSize, uriInfo);
    }
}
