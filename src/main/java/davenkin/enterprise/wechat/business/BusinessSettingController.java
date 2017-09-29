package davenkin.enterprise.wechat.business;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by yteng on 9/27/17.
 */
@RestController
public class BusinessSettingController {
    private static Logger logger = LoggerFactory.getLogger(BusinessSettingController.class);

    @GetMapping(value = "/businessSetting")
    public String businessSetting(HttpServletRequest request) {

        String reqUrl = request.getRequestURL().toString();
        String queryString = request.getQueryString();
        if (queryString != null) {
            reqUrl += "?" + queryString;
        }
        logger.info("business setting page:{}", reqUrl);
        return reqUrl;
    }
}


