package davenkin.enterprise.wechat.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yteng on 9/29/17.
 */
@Component
public class CorpAccessTokenHolder {
    private static String CORP_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_corp_token?suite_access_token={SUITE_ACCESS_TOKEN}";
    private static Logger logger = LoggerFactory.getLogger(CorpAccessTokenHolder.class);
    private SuiteAccessTokenHolder suiteAccessTokenHolder;
    private SuiteProperties suiteProperties;
    private RestTemplate restTemplate;
    private PermanentCodeRepository permanentCodeRepository;

    private Map<String, String> tokens = new HashMap<>();

    @Autowired
    public CorpAccessTokenHolder(SuiteAccessTokenHolder suiteAccessTokenHolder,
                                 SuiteProperties suiteProperties,
                                 RestTemplate restTemplate,
                                 PermanentCodeRepository permanentCodeRepository) {
        this.suiteAccessTokenHolder = suiteAccessTokenHolder;
        this.suiteProperties = suiteProperties;
        this.restTemplate = restTemplate;
        this.permanentCodeRepository = permanentCodeRepository;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void updateCorpAccessToken() {
        logger.info("Try get corp access tokens");
        if (!suiteAccessTokenHolder.hasToken()) {
            logger.info("No suite access token");
            return;
        }
        Map<String, String> codes = permanentCodeRepository.getCodes();
        for (Map.Entry<String, String> entry : codes.entrySet()) {

            String corpId = entry.getKey();
            String permanentCode = entry.getValue();
            logger.info("Try get corp access token for corp[{}]", corpId);
            HashMap<String, String> request = new HashMap<>();
            request.put("suite_id", suiteProperties.getSuiteId());
            request.put("auth_corpid", corpId);
            request.put("permanent_code", permanentCode);
            String url = UriComponentsBuilder.fromHttpUrl(CORP_ACCESS_TOKEN_URL).buildAndExpand(suiteAccessTokenHolder.accessToken()).toString();

            Map response = restTemplate.postForObject(url, request, Map.class);
            String accessToken = (String) response.get("access_token");
            logger.info("Updated access token for corp[{}]:[{}]", corpId, accessToken);
            tokens.put(corpId, accessToken);
        }

    }

    public String accessTokenFor(String corpId) {
        return tokens.get(corpId);
    }
}
