package cloud.microservices.catalog.clients;

import java.time.LocalDateTime;

/**
 * Request DTO for creating a new audit log entry.
 */
public class AuditLogCreateRequest {
    private LocalDateTime timestamp;
    private String action;
    private String entityType;
    private String entityId;
    private String user;
    private String details;
    private String ipAddress;
    private String userAgent;
    private Integer statusCode;

    /**
     * Instantiates a new Audit log create request.
     *
     * @param timestamp  the timestamp
     * @param action     the action
     * @param entityType the entity type
     * @param entityId   the entity id
     * @param user       the user
     * @param details    the details
     * @param ipAddress  the ip address
     * @param userAgent  the user agent
     * @param statusCode the status code
     */
    public AuditLogCreateRequest(LocalDateTime timestamp, String action, String entityType, String entityId, String user, String details, String ipAddress, String userAgent, Integer statusCode) {
        this.timestamp = timestamp;
        this.action = action;
        this.entityType = entityType;
        this.entityId = entityId;
        this.user = user;
        this.details = details;
        this.ipAddress = ipAddress;
        this.userAgent = userAgent;
        this.statusCode = statusCode;
    }

    /**
     * Instantiates a new Audit log create request.
     */
    public AuditLogCreateRequest() {
    }

    /**
     * Create a new audit log request with current timestamp.
     *
     * @param action     the action performed (CREATE, UPDATE, DELETE, READ)
     * @param entityType the type of entity (e.g., "Product")
     * @param entityId   the ID of the entity
     * @param user       the user who performed the action
     * @param details    additional details about the action
     * @return the audit log create request
     */
    public static AuditLogCreateRequest of(String action, String entityType, String entityId,
                                           String user, String details) {
        AuditLogCreateRequest request = new AuditLogCreateRequest();
        request.setTimestamp(LocalDateTime.now());
        request.setAction(action);
        request.setEntityType(entityType);
        request.setEntityId(entityId);
        request.setUser(user);
        request.setDetails(details);
        return request;
    }

    /**
     * Gets timestamp.
     *
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets timestamp.
     *
     * @param timestamp the timestamp
     * @return the timestamp
     */
    public AuditLogCreateRequest setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Gets action.
     *
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Sets action.
     *
     * @param action the action
     * @return the action
     */
    public AuditLogCreateRequest setAction(String action) {
        this.action = action;
        return this;
    }

    /**
     * Gets entity type.
     *
     * @return the entity type
     */
    public String getEntityType() {
        return entityType;
    }

    /**
     * Sets entity type.
     *
     * @param entityType the entity type
     * @return the entity type
     */
    public AuditLogCreateRequest setEntityType(String entityType) {
        this.entityType = entityType;
        return this;
    }

    /**
     * Gets entity id.
     *
     * @return the entity id
     */
    public String getEntityId() {
        return entityId;
    }

    /**
     * Sets entity id.
     *
     * @param entityId the entity id
     * @return the entity id
     */
    public AuditLogCreateRequest setEntityId(String entityId) {
        this.entityId = entityId;
        return this;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     * @return the user
     */
    public AuditLogCreateRequest setUser(String user) {
        this.user = user;
        return this;
    }

    /**
     * Gets details.
     *
     * @return the details
     */
    public String getDetails() {
        return details;
    }

    /**
     * Sets details.
     *
     * @param details the details
     * @return the details
     */
    public AuditLogCreateRequest setDetails(String details) {
        this.details = details;
        return this;
    }

    /**
     * Gets ip address.
     *
     * @return the ip address
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets ip address.
     *
     * @param ipAddress the ip address
     * @return the ip address
     */
    public AuditLogCreateRequest setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    /**
     * Gets user agent.
     *
     * @return the user agent
     */
    public String getUserAgent() {
        return userAgent;
    }

    /**
     * Sets user agent.
     *
     * @param userAgent the user agent
     * @return the user agent
     */
    public AuditLogCreateRequest setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    /**
     * Gets status code.
     *
     * @return the status code
     */
    public Integer getStatusCode() {
        return statusCode;
    }

    /**
     * Sets status code.
     *
     * @param statusCode the status code
     * @return the status code
     */
    public AuditLogCreateRequest setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        AuditLogCreateRequest that = (AuditLogCreateRequest) o;
        return timestamp.equals(that.timestamp) && action.equals(that.action) && entityType.equals(that.entityType) && entityId.equals(that.entityId) && user.equals(that.user) && details.equals(that.details) && ipAddress.equals(that.ipAddress) && userAgent.equals(that.userAgent) && statusCode.equals(that.statusCode);
    }

    @Override
    public int hashCode() {
        int result = timestamp.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + entityType.hashCode();
        result = 31 * result + entityId.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + details.hashCode();
        result = 31 * result + ipAddress.hashCode();
        result = 31 * result + userAgent.hashCode();
        result = 31 * result + statusCode.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AuditLogCreateRequest{" +
                "timestamp=" + timestamp +
                ", action='" + action + '\'' +
                ", entityType='" + entityType + '\'' +
                ", entityId='" + entityId + '\'' +
                ", user='" + user + '\'' +
                ", details='" + details + '\'' +
                ", ipAddress='" + ipAddress + '\'' +
                ", userAgent='" + userAgent + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}