package davenkin.enterprise.wechat.suite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yteng on 9/29/17.
 */

@Component
public class PermanentCodeService {
    private static String PERMANENT_CODE_URL = "https://qyapi.weixin.qq.com/cgi-bin/service/get_permanent_code?suite_access_token={suiteAccessToken}";
    private SuiteProperties suiteProperties;
    private SuiteAccessTokenHolder suiteAccessTokenHolder;
    private RestTemplate restTemplate;
    private PermanentCodeRepository permanentCodeRepository;

    @Autowired
    public PermanentCodeService(SuiteProperties suiteProperties, SuiteAccessTokenHolder suiteAccessTokenHolder, RestTemplate restTemplate, PermanentCodeRepository permanentCodeRepository) {
        this.suiteProperties = suiteProperties;
        this.suiteAccessTokenHolder = suiteAccessTokenHolder;
        this.restTemplate = restTemplate;
        this.permanentCodeRepository = permanentCodeRepository;
    }

    public Map registerCorp(String authCode) {
        HashMap<String, String> request = new HashMap<>();
        request.put("suite_id", suiteProperties.getSuiteId());
        request.put("auth_code", authCode);

        String url = UriComponentsBuilder.fromHttpUrl(PERMANENT_CODE_URL).buildAndExpand(suiteAccessTokenHolder.accessToken()).toString();

        Map map = restTemplate.postForObject(url, request, Map.class);
        String permanent_code = (String) map.get("permanent_code");
        Object auth_corp_info = map.get("auth_corp_info");
        String corpid = (String) ((Map) auth_corp_info).get("corpid");
        System.out.println("===corp id" + corpid);
        System.out.println("===auth corp info" + auth_corp_info.getClass().toString());
        permanentCodeRepository.save(corpid, permanent_code);
        return map;
    }
}
