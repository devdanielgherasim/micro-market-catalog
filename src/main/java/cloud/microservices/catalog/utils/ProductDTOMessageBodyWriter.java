package cloud.microservices.catalog.utils;

import cloud.microservices.catalog.dtos.ProductDTO;
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
import java.lang.reflect.Type;

/**
 * Custom message body writer for ProductDTO objects.
 * Ensures proper JSON serialization of ProductDTO objects.
 */
@Provider
@Produces(MediaType.APPLICATION_JSON)
public class ProductDTOMessageBodyWriter implements MessageBodyWriter<ProductDTO> {

    private final Jsonb jsonb = JsonbBuilder.create();

    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        // Check if the type is ProductDTO
        return ProductDTO.class.isAssignableFrom(type);
    }

    @Override
    public void writeTo(ProductDTO productDTO, Class<?> type, Type genericType, Annotation[] annotations,
                        MediaType mediaType, MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream)
            throws IOException, WebApplicationException {
        
        // Serialize the ProductDTO object to JSON
        jsonb.toJson(productDTO, entityStream);
    }
}