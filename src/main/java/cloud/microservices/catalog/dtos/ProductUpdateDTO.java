package cloud.microservices.catalog.dtos;

import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object for updating an existing Product.
 */
public class ProductUpdateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 3L;
    private String name;
    private String description;
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    private String category;
    private Boolean isAvailable;

    /**
     * Instantiates a new Product update dto.
     *
     * @param name         the name
     * @param description  the description
     * @param price        the price
     * @param category     the category
     * @param isAvailable  the is available
     */
    public ProductUpdateDTO(String name, String description, BigDecimal price, String category, Boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    /**
     * Instantiates a new Product update dto.
     */
    public ProductUpdateDTO() {
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     * @return the name
     */
    public ProductUpdateDTO setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     * @return the description
     */
    public ProductUpdateDTO setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     * @return the price
     */
    public ProductUpdateDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    /**
     * Gets category.
     *
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets category.
     *
     * @param category the category
     * @return the category
     */
    public ProductUpdateDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    /**
     * Gets available.
     *
     * @return the available
     */
    public Boolean getAvailable() {
        return isAvailable;
    }

    /**
     * Sets available.
     *
     * @param available the available
     * @return the available
     */
    public ProductUpdateDTO setAvailable(Boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductUpdateDTO that = (ProductUpdateDTO) o;
        return Objects.equals(name, that.name) && 
               Objects.equals(description, that.description) && 
               Objects.equals(price, that.price) && 
               Objects.equals(category, that.category) && 
               Objects.equals(isAvailable, that.isAvailable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, category, isAvailable);
    }

    @Override
    public String toString() {
        return "ProductUpdateDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
