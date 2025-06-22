package cloud.microservices.catalog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Product.
 */
@Entity
@Table(name = "products")
public class Product extends PanacheEntity implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Product name is required")
    @Column(nullable = false)
    private String name;
    @Column(length = 2000)
    private String description;
    @NotNull(message = "Price is required")
    @Positive(message = "Price must be positive")
    @Column(nullable = false)
    private BigDecimal price;
    @Column(nullable = false)
    private String category;
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    /**
     * Instantiates a new Product.
     *
     * @param name         the name
     * @param description  the description
     * @param price        the price
     * @param category     the category
     * @param isAvailable  the is available
     */
    public Product(String name, String description, BigDecimal price, String category, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.isAvailable = isAvailable;
    }

    /**
     * Instantiates a new Product.
     */
    public Product() {
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
    public Product setName(String name) {
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
    public Product setDescription(String description) {
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
    public Product setPrice(BigDecimal price) {
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
    public Product setCategory(String category) {
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
    public Product setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return isAvailable == product.isAvailable && 
               Objects.equals(id, product.id) &&
               Objects.equals(name, product.name) && 
               Objects.equals(description, product.description) && 
               Objects.equals(price, product.price) && 
               Objects.equals(category, product.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, price, category, isAvailable);
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}
