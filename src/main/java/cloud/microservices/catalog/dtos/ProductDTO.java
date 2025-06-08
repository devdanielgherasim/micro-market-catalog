package cloud.microservices.catalog.dtos;


import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Data Transfer Object for Product entity.
 */
public class ProductDTO {
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private String category;
    private String version;
    private LocalDate releaseDate;
    private String publisher;
    private String features;
    private String requirements;
    private boolean isAvailable;

    /**
     * Instantiates a new Product dto.
     *
     * @param id           the id
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
    public ProductDTO(Long id, String name, String description, BigDecimal price, String category, String version, LocalDate releaseDate, String publisher, String features, String requirements, boolean isAvailable) {
        this.id = id;
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
    public ProductDTO setVersion(String version) {
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
    public ProductDTO setReleaseDate(LocalDate releaseDate) {
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
    public ProductDTO setPublisher(String publisher) {
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
    public ProductDTO setFeatures(String features) {
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
    public ProductDTO setRequirements(String requirements) {
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
    public ProductDTO setAvailable(boolean available) {
        isAvailable = available;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        ProductDTO that = (ProductDTO) o;
        return isAvailable == that.isAvailable && id.equals(that.id) && name.equals(that.name) && description.equals(that.description) && price.equals(that.price) && category.equals(that.category) && version.equals(that.version) && releaseDate.equals(that.releaseDate) && publisher.equals(that.publisher) && features.equals(that.features) && requirements.equals(that.requirements);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + description.hashCode();
        result = 31 * result + price.hashCode();
        result = 31 * result + category.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + releaseDate.hashCode();
        result = 31 * result + publisher.hashCode();
        result = 31 * result + features.hashCode();
        result = 31 * result + requirements.hashCode();
        result = 31 * result + Boolean.hashCode(isAvailable);
        return result;
    }

    @Override
    public String toString() {
        return "ProductDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
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
