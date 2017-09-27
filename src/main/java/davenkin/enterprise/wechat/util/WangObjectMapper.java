package davenkin.enterprise.wechat.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import davenkin.enterprise.wechat.exception.JacksonSerializationException;

/**
 * Created by yteng on 6/26/17.
 */
public class WangObjectMapper extends ObjectMapper {

    @Override
    public String writeValueAsString(Object value) {
        try {
            return super.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            throw new JacksonSerializationException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T readValue(String content, Class<T> valueType) {
        try {
            return super.readValue(content, valueType);
        } catch (Exception e) {
            throw new JacksonSerializationException(e.getMessage(), e);
        }
    }

    @Override
    public <T> T readValue(String content, TypeReference valueTypeRef) {
        try {
            return super.readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new JacksonSerializationException(e.getMessage(), e);
        }
    }

}
