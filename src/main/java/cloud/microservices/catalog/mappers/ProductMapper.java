package cloud.microservices.catalog.mappers;

import cloud.microservices.catalog.dtos.ProductCreateDTO;
import cloud.microservices.catalog.dtos.ProductDTO;
import cloud.microservices.catalog.dtos.ProductUpdateDTO;
import cloud.microservices.catalog.entities.Product;
import jakarta.enterprise.context.ApplicationScoped;

/**
 * Mapper class for converting between Product entity and DTOs.
 */
@ApplicationScoped
public class ProductMapper {

    /**
     * Map Product entity to ProductDTO.
     *
     * @param product the product entity
     * @return the product DTO
     */
    public ProductDTO toDTO(Product product) {
        if (product == null) {
            return null;
        }

        return new ProductDTO(
                product.id,
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getCategory(),
                product.isAvailable()
        );
    }

    /**
     * Map ProductCreateDTO to Product entity.
     *
     * @param dto     the product create DTO
     * @param product the product entity to update
     */
    public void fromCreateDTO(ProductCreateDTO dto, Product product) {
        if (dto == null || product == null) {
            return;
        }

        product.setName(dto.getName());
        product.setDescription(dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setCategory(dto.getCategory());
        product.setAvailable(dto.isAvailable());
    }

    /**
     * Map ProductUpdateDTO to Product entity.
     *
     * @param dto     the product update DTO
     * @param product the product entity to update
     */
    public void fromUpdateDTO(ProductUpdateDTO dto, Product product) {
        if (dto == null || product == null) {
            return;
        }

        if (dto.getName() != null) {
            product.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            product.setDescription(dto.getDescription());
        }
        if (dto.getPrice() != null) {
            product.setPrice(dto.getPrice());
        }
        if (dto.getCategory() != null) {
            product.setCategory(dto.getCategory());
        }
        if (dto.getAvailable() != null) {
            product.setAvailable(dto.getAvailable());
        }
    }
}
