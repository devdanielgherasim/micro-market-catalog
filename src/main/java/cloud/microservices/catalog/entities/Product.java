package cloud.microservices.catalog.entities;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 * The type Product.
 */
@Entity
@Table(name = "products")
public class Product extends PanacheEntity {
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
    private String version;
    @Column(name = "release_date")
    private LocalDate releaseDate;
    private String publisher;
    @Column(length = 2000)
    private String features;
    @Column(length = 1000)
    private String requirements;
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable = true;

    /**
     * Instantiates a new Product.
     *
     * @param name         the name
     * @param description  the description
     * @param price        the price
     * @param category     the category
     * @param version      the version
     * @param releaseDate  the release date
     * @param publisher    the publisher
     * @param features     the features
     * @param requirements the requirements
     * @param isAvailable  the is available
     */
    public Product(String name, String description, BigDecimal price, String category, String version, LocalDate releaseDate, String publisher, String features, String requirements, boolean isAvailable) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.version = version;
        this.releaseDate = releaseDate;
        this.publisher = publisher;
        this.features = features;
        this.requirements = requirements;
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
     * Gets version.
     *
     * @return the version
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets version.
     *
     * @param version the version
     * @return the version
     */
    public Product setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Gets release date.
     *
     * @return the release date
     */
    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    /**
     * Sets release date.
     *
     * @param releaseDate the release date
     * @return the release date
     */
    public Product setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
        return this;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     * @return the publisher
     */
    public Product setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    /**
     * Gets features.
     *
     * @return the features
     */
    public String getFeatures() {
        return features;
    }

    /**
     * Sets features.
     *
     * @param features the features
     * @return the features
     */
    public Product setFeatures(String features) {
        this.features = features;
        return this;
    }

    /**
     * Gets requirements.
     *
     * @return the requirements
     */
    public String getRequirements() {
        return requirements;
    }

    /**
     * Sets requirements.
     *
     * @param requirements the requirements
     * @return the requirements
     */
    public Product setRequirements(String requirements) {
        this.requirements = requirements;
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
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return isAvailable == product.isAvailable && Objects.equals(name, product.name) && Objects.equals(description, product.description) && Objects.equals(price, product.price) && Objects.equals(category, product.category) && Objects.equals(version, product.version) && Objects.equals(releaseDate, product.releaseDate) && Objects.equals(publisher, product.publisher) && Objects.equals(features, product.features) && Objects.equals(requirements, product.requirements);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(name);
        result = 31 * result + Objects.hashCode(description);
        result = 31 * result + Objects.hashCode(price);
        result = 31 * result + Objects.hashCode(category);
        result = 31 * result + Objects.hashCode(version);
        result = 31 * result + Objects.hashCode(releaseDate);
        result = 31 * result + Objects.hashCode(publisher);
        result = 31 * result + Objects.hashCode(features);
        result = 31 * result + Objects.hashCode(requirements);
        result = 31 * result + Boolean.hashCode(isAvailable);
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", version='" + version + '\'' +
                ", releaseDate=" + releaseDate +
                ", publisher='" + publisher + '\'' +
                ", features='" + features + '\'' +
                ", requirements='" + requirements + '\'' +
                ", isAvailable=" + isAvailable +
                '}';
    }
}