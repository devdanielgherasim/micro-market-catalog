package cloud.microservices.catalog.dtos;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * Data Transfer Object for Product entity.
 */
@JsonSerialize
@JsonAutoDetect(fieldVisibility = Visibility.ANY, getterVisibility = Visibility.ANY, setterVisibility = Visibility.ANY)
public class ProductDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("price")
    private BigDecimal price;
    @JsonProperty("category")
    private String category;
    @JsonProperty("isAvailable")
    private boolean isAvailable;

    /**
     * Instantiates a new Product dto.
     *
     * @param id          the id
     * @param name        the name
     * @param description the description
     * @param price       the price
     * @param category    the category
     * @param isAvailable the is available
     */
    public ProductDTO(Long id, String name, String description, BigDecimal price, String category, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    /**
     * Instantiates a new Product dto.
     */
    public ProductDTO() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     * @return the id
     */
    public ProductDTO setId(Long id) {
        this.id = id;
        return this;
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
    public ProductDTO setName(String name) {
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
    public ProductDTO setDescription(String description) {
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
    public ProductDTO setPrice(BigDecimal price) {
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
    public ProductDTO setCategory(String category) {
        this.category = category;
        return this;
    }

    /**
     * Is available boolean.
     *
     * @return the boolean
     */
    @JsonProperty("available")
    public boolean isAvailable() {
        return isAvailable;
    }

    /**
     * Sets available.
     *
     * @param available the available
     * @return the available
     */
    public ProductDTO setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductDTO that = (ProductDTO) o;
        return isAvailable == that.isAvailable &&
                Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(description, that.description) &&
                Objects.equals(price, that.price) &&
                Objects.equals(category, that.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, category, isAvailable);
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
