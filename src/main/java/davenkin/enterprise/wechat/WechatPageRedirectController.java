package davenkin.enterprise.wechat;

import davenkin.enterprise.wechat.suite.CorpAccessTokenHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

/**
 * Created by yteng on 9/29/17.
 */
@Controller
public class WechatPageRedirectController {

    private static String USER_INFO_URL="https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token={ACCESS_TOKEN}&code={CODE}";

    @Autowired
    private CorpAccessTokenHolder corpAccessTokenHolder;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/wechat/auth/redirect")
    public String redirect(@RequestParam("code") String code,
                        @RequestParam("state") String corpId) {
        String accessTokenFor = corpAccessTokenHolder.accessTokenFor(corpId);
        String url = UriComponentsBuilder.fromHttpUrl(USER_INFO_URL).buildAndExpand(accessTokenFor, code).toString();
        Map forObject = restTemplate.getForObject(url, Map.class);
        String userId = (String) forObject.get("UserId");
        System.out.println("===userid:"+userId);
        return "redirect:https://www.wanghushengri.com/enterprise-wechat-3rd-provider/app/index.html";
//        return "Success get permanent code and register corp";
    }
}
