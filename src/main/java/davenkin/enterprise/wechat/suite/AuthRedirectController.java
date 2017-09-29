package davenkin.enterprise.wechat.suite;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by yteng on 9/29/17.
 */
@Controller
public class AuthRedirectController {

    @GetMapping("/redirect")
    public String redirect(@RequestParam("auth_code") String authCode,
                           @RequestParam("expires_in") Long expiresIn,
                           @RequestParam("state") String state) {
        return null;
    }
}
