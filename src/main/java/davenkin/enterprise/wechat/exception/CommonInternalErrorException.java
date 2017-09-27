package davenkin.enterprise.wechat.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by yteng on 6/21/17.
 */

@ResponseStatus(INTERNAL_SERVER_ERROR)
public class CommonInternalErrorException extends RuntimeException {
    public CommonInternalErrorException(String message) {
        super(message);
    }

    public CommonInternalErrorException(String message, Throwable throwable) {
        super(message, throwable);
    }


}
