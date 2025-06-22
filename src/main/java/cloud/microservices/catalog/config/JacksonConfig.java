package cloud.microservices.catalog.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Singleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configuration class for Jackson ObjectMapper.
 * Provides a customized ObjectMapper bean for the application with appropriate
 * configuration for handling JSON serialization and deserialization.
 */
@ApplicationScoped
public class JacksonConfig {
    private static final Logger logger = LoggerFactory.getLogger(JacksonConfig.class);

    /**
     * Produces a customized ObjectMapper bean with configuration optimized for the application.
     *
     * <p>This configuration includes:
     * <ul>
     *   <li>Support for Java 8 date/time types</li>
     *   <li>Parameter names module for better constructor deserialization</li>
     *   <li>Pretty printing for readable JSON output</li>
     *   <li>ISO-8601 date format</li>
     *   <li>Ignoring unknown properties during deserialization</li>
     *   <li>Excluding null values from serialization</li>
     * </ul>
     *
     * @return the customized ObjectMapper
     */
    @Produces
    @Singleton
    public ObjectMapper objectMapper() {
        logger.debug("Initializing customized ObjectMapper");

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new ParameterNamesModule());

        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        logger.debug("ObjectMapper initialized with custom configuration");
        return objectMapper;
    }
}
