package davenkin.enterprise.wechat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by yteng on 11/29/16.
 */

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class JacksonSerializationException extends RuntimeException {
    public JacksonSerializationException(String message, Throwable e) {
        super(message, e);
    }
}
