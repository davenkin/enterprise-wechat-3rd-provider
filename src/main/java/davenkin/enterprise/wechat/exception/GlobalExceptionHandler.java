package davenkin.enterprise.wechat.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static davenkin.enterprise.wechat.exception.DeveloperMessageUtil.developerMessage;
import static davenkin.enterprise.wechat.exception.ErrorResponse.of;
import static org.springframework.core.annotation.AnnotatedElementUtils.findMergedAnnotation;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/**
 * Created by yteng on 11/27/16.
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler()
    @ResponseBody
    public ResponseEntity<?> handleException(Exception ex) {
        ex.printStackTrace();
        HttpStatus httpStatus = resolveAnnotatedResponseStatus(ex);
        String message = ex.getMessage();
        if (message == null) {
            message = "Internal error";
        }

        return new ResponseEntity<>(of(httpStatus.value(), message, developerMessage(ex)), new HttpHeaders(), httpStatus);
    }


    private HttpStatus resolveAnnotatedResponseStatus(Exception exception) {
        logger.error(exception.getMessage());

        ResponseStatus annotation = findMergedAnnotation(exception.getClass(), ResponseStatus.class);
        if (annotation != null) {
            return annotation.value();
        }

        return INTERNAL_SERVER_ERROR;
    }


}
