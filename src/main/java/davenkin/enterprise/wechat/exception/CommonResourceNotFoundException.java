package davenkin.enterprise.wechat.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by yteng on 6/23/17.
 */

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommonResourceNotFoundException extends RuntimeException {
    public CommonResourceNotFoundException(String message) {
        super(message);
    }

    public CommonResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
