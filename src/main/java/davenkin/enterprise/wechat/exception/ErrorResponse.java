package davenkin.enterprise.wechat.exception;

/**
 * Created by yteng on 11/27/16.
 */
public class ErrorResponse {
    private int statusCode;
    private String errorMessage;
    private String developerMessage;

    private ErrorResponse(int statusCode, String errorMessage, String developerMessage) {
        this.statusCode = statusCode;
        this.errorMessage = errorMessage;
        this.developerMessage = developerMessage;
    }

    public static ErrorResponse of(int httpStatusCode, String errorMessage, String developerMessage) {
        return new ErrorResponse(httpStatusCode, errorMessage, developerMessage);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }
}
