package cloud.microservices.catalog.serialization;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyReader;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Generic MessageBodyReader for deserializing HTTP request bodies.
 * This class uses the SerializationProvider to handle deserialization of objects
 * from various content types.
 */
@Provider
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GenericMessageBodyReader implements MessageBodyReader<Object> {

    private static final Logger logger = LoggerFactory.getLogger(GenericMessageBodyReader.class);

    @Inject
    private SerializationProvider serializationProvider;

    @Override
    public boolean isReadable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        boolean isSupported = MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType);

        if (isSupported) {
            logger.debug("Can read type {} with media type {}", type.getName(), mediaType);
        } else {
            logger.debug("Cannot read type {} with media type {}", type.getName(), mediaType);
        }

        return isSupported;
    }

    @Override
    public Object readFrom(Class<Object> type, Type genericType, Annotation[] annotations,
                           MediaType mediaType, MultivaluedMap<String, String> httpHeaders, InputStream entityStream)
            throws WebApplicationException {

        logger.debug("Reading object of type {} with media type {}", type.getName(), mediaType);

        try {
            return serializationProvider.deserialize(entityStream, type, genericType, mediaType);
        } catch (IOException e) {
            logger.error("Error deserializing request body to type {}: {}", type.getName(), e.getMessage());
            throw new WebApplicationException("Error deserializing request body: " + e.getMessage(), e, 400);
        } catch (UnsupportedOperationException e) {
            logger.error("Unsupported media type {} for type {}: {}", mediaType, type.getName(), e.getMessage());
            throw new WebApplicationException("Unsupported media type: " + mediaType, e, 415);
        } catch (Exception e) {
            logger.error("Unexpected error deserializing request body to type {}: {}", type.getName(), e.getMessage());
            throw new WebApplicationException("Error processing request: " + e.getMessage(), e, 500);
        }
    }
}