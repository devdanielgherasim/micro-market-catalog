package cloud.microservices.catalog.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object for creating a new Product.
 */
public class ProductCreateDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 2L;

    @NotBlank(message = "Product name is required")
    private String name;
    private String description;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    @NotBlank(message = "Category is required")
    private String category;
    private boolean isAvailable = true;

    /**
     * Instantiates a new Product create dto.
     *
     * @param name         the name
     * @param description  the description
     * @param price        the price
     * @param category     the category
     * @param isAvailable  the is available
     */
    public ProductCreateDTO(String name, String description, BigDecimal price, String category, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    /**
     * Instantiates a new Product create dto.
     */
    public ProductCreateDTO() {
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
    public ProductCreateDTO setName(String name) {
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
    public ProductCreateDTO setDescription(String description) {
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
    public ProductCreateDTO setPrice(BigDecimal price) {
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
    public ProductCreateDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets available.
     *
     * @param available the available
     * @return the available
     */
    public ProductCreateDTO setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductCreateDTO that = (ProductCreateDTO) o;
        return isAvailable == that.isAvailable && 
               Objects.equals(name, that.name) && 
               Objects.equals(description, that.description) && 
               Objects.equals(price, that.price) && 
               Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, price, category, isAvailable);
    }

    @Override
    public String toString() {
        return "ProductCreateDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
