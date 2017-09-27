package davenkin.enterprise.wechat.exception;

/**
 * Created by yteng on 8/29/17.
 */
public class DeveloperMessageUtil {
    public static String developerMessage(Throwable ex) {
        return ex.getCause() != null ? ex.getCause().getMessage() : null;
    }
}
