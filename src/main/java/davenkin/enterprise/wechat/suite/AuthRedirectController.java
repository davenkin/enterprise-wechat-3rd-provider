package davenkin.enterprise.wechat.suite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by yteng on 9/29/17.
 */
@RestController
public class AuthRedirectController {

    @Autowired
    private PermanentCodeService permanentCodeService;

    @GetMapping("/suite/auth/redirect")
    public Map redirect(@RequestParam("auth_code") String authCode,
                        @RequestParam("expires_in") Long expiresIn,
                        @RequestParam("state") String state) {
        return permanentCodeService.registerCorp(authCode);
//        return "Success get permanent code and register corp";
    }
}
