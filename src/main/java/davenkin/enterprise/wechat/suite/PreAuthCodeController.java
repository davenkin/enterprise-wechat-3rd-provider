package davenkin.enterprise.wechat.suite;

import davenkin.enterprise.wechat.exception.CommonInternalErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;

/**
 * Created by yteng on 9/29/17.
 */
@RestController
public class PreAuthCodeController {
    private static String PRE_AUTH_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_pre_auth_code?suite_access_token=";
    private RestTemplate restTemplate;

    private SuiteAccessTokenHolder suiteAccessTokenHolder;

    private SuiteProperties suiteProperties;

    @Autowired
    public PreAuthCodeController(RestTemplate restTemplate, SuiteAccessTokenHolder suiteAccessTokenHolder, SuiteProperties suiteProperties) {
        this.restTemplate = restTemplate;
        this.suiteAccessTokenHolder = suiteAccessTokenHolder;
        this.suiteProperties = suiteProperties;
    }

    @GetMapping("/preAuthCode")
    public PreAuthCode getPreAuthCode() {
        if (!suiteAccessTokenHolder.hasToken()) {
            throw new CommonInternalErrorException("No suite access token available");
        }

        String suiteAccessToken = suiteAccessTokenHolder.accessToken();
        String suiteId = suiteProperties.getSuiteId();

        HashMap<String, String> request = new HashMap<>();
        request.put("suite_id", suiteId);

        String url = UriComponentsBuilder.fromPath(PRE_AUTH_CODE_URL).buildAndExpand(suiteAccessToken).toString();
        PreAuthCodeResponse response = restTemplate.postForObject(PRE_AUTH_CODE_URL + suiteAccessToken, request, PreAuthCodeResponse.class);

        return new PreAuthCode(response.getPre_auth_code());
    }

}
