package davenkin.enterprise.wechat.util;

import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * Created by yteng on 8/29/17.
 */
public class TimeUtil {
    public static final ZoneId DEFAULT_ZONE = ZoneId.of("Asia/Shanghai");

    public static ZonedDateTime now() {
        return ZonedDateTime.now(DEFAULT_ZONE);
    }
}
