package cloud.microservices.catalog.utils;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Custom message body writer for PageResponse objects.
 * Ensures proper JSON serialization of PageResponse objects.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class PageResponseMessageBodyWriter implements MessageBodyWriter<PageResponse<?>> {

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        // Check if the type is PageResponse
        if (!PageResponse.class.isAssignableFrom(type)) {
            return false;
        }

        // If genericType is a ParameterizedType, ensure it's PageResponse<?>
        if (genericType instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericType;
            return parameterizedType.getRawType() == PageResponse.class;
        }

        return true;
    }

    @Override
    public void writeTo(PageResponse<?> pageResponse, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {
        
        // Serialize the PageResponse object to JSON
        jsonb.toJson(pageResponse, entityStream);
    }
}