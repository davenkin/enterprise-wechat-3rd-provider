package davenkin.enterprise.wechat.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yteng on 9/29/17.
 */

@Component
public class SuiteAccessTokenHolder {
    private Logger logger = LoggerFactory.getLogger(SuiteAccessTokenHolder.class);
    private String SUITE_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_suite_token";
    private RestTemplate restTemplate;
    private SuiteTicketHolder suiteTicketHolder;
    private SuiteProperties suiteProperties;

    @Autowired
    public SuiteAccessTokenHolder(RestTemplate restTemplate,
                                  SuiteTicketHolder suiteTicketHolder,
                                  SuiteProperties suiteProperties) {
        this.restTemplate = restTemplate;
        this.suiteTicketHolder = suiteTicketHolder;
        this.suiteProperties = suiteProperties;
    }

    @Scheduled(cron = "0 0/15 * * * ?")
    public void updateSuiteAccessToken() {
        SuiteAccessTokenRequest request = new SuiteAccessTokenRequest(suiteProperties.getSuiteId(), suiteProperties.getSuiteSecret(), suiteTicketHolder.ticket());
        if (suiteTicketHolder.hasTicket()) {
            String s = restTemplate.postForObject(SUITE_ACCESS_TOKEN_URL, request, String.class);
            logger.info("Updated suite access token:{}", s);
        }
    }

}
