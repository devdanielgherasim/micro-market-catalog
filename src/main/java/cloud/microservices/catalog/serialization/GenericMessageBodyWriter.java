package cloud.microservices.catalog.serialization;

import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Generic MessageBodyWriter for serializing objects to HTTP response bodies.
 * This class uses the SerializationProvider to handle serialization of objects
 * to various content types.
 */
@Provider
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class GenericMessageBodyWriter implements MessageBodyWriter<Object> {

    private static final Logger logger = LoggerFactory.getLogger(GenericMessageBodyWriter.class);

    @Inject
    private SerializationProvider serializationProvider;

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        boolean isSupported = MediaType.APPLICATION_JSON_TYPE.isCompatible(mediaType);

        if (isSupported) {
            logger.debug("Can write type {} with media type {}", type.getName(), mediaType);
        } else {
            logger.debug("Cannot write type {} with media type {}", type.getName(), mediaType);
        }

        return isSupported;
    }

    @Override
    public void writeTo(Object object, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws WebApplicationException {

        logger.debug("Writing object of type {} with media type {}", object.getClass().getName(), mediaType);

        httpHeaders.putSingle("Content-Type", mediaType.toString());

        try {
            serializationProvider.serialize(object, entityStream, mediaType);
        } catch (IOException e) {
            logger.error("Error serializing object of type {}: {}", object.getClass().getName(), e.getMessage());
            throw new WebApplicationException("Error serializing response: " + e.getMessage(), e, 500);
        } catch (UnsupportedOperationException e) {
            logger.error("Unsupported media type {} for type {}: {}", mediaType, object.getClass().getName(), e.getMessage());
            throw new WebApplicationException("Unsupported media type: " + mediaType, e, 415);
        } catch (Exception e) {
            logger.error("Unexpected error serializing object of type {}: {}", object.getClass().getName(), e.getMessage());
            throw new WebApplicationException("Error processing response: " + e.getMessage(), e, 500);
        }
    }
}