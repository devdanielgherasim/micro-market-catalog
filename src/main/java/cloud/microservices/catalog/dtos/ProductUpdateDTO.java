package cloud.microservices.catalog.dtos;

import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for updating an existing Product.
 */
public class ProductUpdateDTO {
    private String name;
    private String description;
    @Positive(message = "Price must be positive")
    private BigDecimal price;
    private String category;
    private String version;
    private LocalDate releaseDate;
    private String publisher;
    private String features;
    private String requirements;
    private Boolean isAvailable;

    /**
     * Instantiates a new Product update dto.
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
    public ProductUpdateDTO(String name, String description, BigDecimal price, String category, String version, LocalDate releaseDate, String publisher, String features, String requirements, Boolean isAvailable) {
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
    public ProductUpdateDTO setVersion(String version) {
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
    public ProductUpdateDTO setReleaseDate(LocalDate releaseDate) {
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
    public ProductUpdateDTO setPublisher(String publisher) {
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
    public ProductUpdateDTO setFeatures(String features) {
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
    public ProductUpdateDTO setRequirements(String requirements) {
        this.requirements = requirements;
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
        if (o == null || getClass() != o.getClass()) return false;

        ProductUpdateDTO that = (ProductUpdateDTO) o;
        return name.equals(that.name) && description.equals(that.description) && price.equals(that.price) && category.equals(that.category) && version.equals(that.version) && releaseDate.equals(that.releaseDate) && publisher.equals(that.publisher) && features.equals(that.features) && requirements.equals(that.requirements) && isAvailable.equals(that.isAvailable);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + features.hashCode();
        result = 31 * result + requirements.hashCode();
        result = 31 * result + isAvailable.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "ProductUpdateDTO{" +
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
