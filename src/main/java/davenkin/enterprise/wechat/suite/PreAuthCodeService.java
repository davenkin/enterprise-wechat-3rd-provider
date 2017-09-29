package davenkin.enterprise.wechat.suite;

import davenkin.enterprise.wechat.exception.CommonInternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

/**
 * Created by yteng on 9/29/17.
 */
@Component
public class PreAuthCodeService {
    private static String PRE_AUTH_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code?suite_access_token={suiteAccessToken}";
    private RestTemplate restTemplate;

    private SuiteAccessTokenHolder suiteAccessTokenHolder;

    private SuiteProperties suiteProperties;

    @Autowired
    public PreAuthCodeService(RestTemplate restTemplate, SuiteAccessTokenHolder suiteAccessTokenHolder, SuiteProperties suiteProperties) {
        this.restTemplate = restTemplate;
        this.suiteAccessTokenHolder = suiteAccessTokenHolder;
        this.suiteProperties = suiteProperties;
    }

    public String getPreAuthCode() {
        if (!suiteAccessTokenHolder.hasToken()) {
            throw new CommonInternalErrorException("No suite access token available");
        }

        String suiteAccessToken = suiteAccessTokenHolder.accessToken();
        String suiteId = suiteProperties.getSuiteId();

        HashMap<String, String> request = new HashMap<>();
        request.put("suite_id", suiteId);

        String url = UriComponentsBuilder.fromHttpUrl(PRE_AUTH_CODE_URL).buildAndExpand(suiteAccessToken).toString();
        PreAuthCodeResponse response = restTemplate.postForObject(url, request, PreAuthCodeResponse.class);
        return response.getPre_auth_code();
    }
}
