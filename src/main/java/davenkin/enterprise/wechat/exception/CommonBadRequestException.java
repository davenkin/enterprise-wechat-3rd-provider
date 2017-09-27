package davenkin.enterprise.wechat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

/**
 * Created by yteng on 6/21/17.
 */
@ResponseStatus(BAD_REQUEST)
public class CommonBadRequestException extends RuntimeException {
    public CommonBadRequestException(String message) {
        super(message);
    }

    public CommonBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }
}
