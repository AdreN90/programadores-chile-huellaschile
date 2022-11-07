package cl.programadoreschile.adrian.veterinary.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jooq.lambda.Unchecked;

import java.util.function.Function;

public class APIUtils {

    private APIUtils() {
    }

    public static <T> T castObject(Object obj, Class<T> clazz) {
        return objectMapperProcessor(Unchecked.function(objectMapper -> objectMapper.readValue(toJsonString(obj), clazz)));
    }

    public static String toJsonString(Object stringObjectMap) {
        return objectMapperProcessor(Unchecked.function(objectMapper -> objectMapper.writeValueAsString(stringObjectMap)));
    }

    public static <R> R objectMapperProcessor(Function<ObjectMapper, R> code) {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return code.apply(objectMapper);
    }

    public static String formatDoubleQuotes(String value) {
        return value.replace("\"", "");
    }

}
