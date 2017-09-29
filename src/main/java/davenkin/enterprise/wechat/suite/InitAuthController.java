package davenkin.enterprise.wechat.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yteng on 9/27/17.
 */
@Controller
public class InitAuthController {
    private static Logger logger = LoggerFactory.getLogger(InitAuthController.class);

    private PreAuthCodeService preAuthCodeService;

    @Autowired
    public InitAuthController(PreAuthCodeService preAuthCodeService) {
        this.preAuthCodeService = preAuthCodeService;
    }

    @GetMapping(value = "/initAuth")
    public void initAuth(HttpServletResponse response) throws IOException {
        String s = preAuthCodeService.getPreAuthCode();
        String s1 = "<a href=\"https://open.work.weixin.qq.com/3rdapp/install\n" +
                "?suite_id=tj6fec3ab3fba4847a&pre_auth_code=" + s + "&redirect_uri=https://www.wanghushengri.com/enterprise-wechat-3rd-provider/suite/auth/redirect&state=STATE1\">start</a>\n";
        response.getWriter().println(s1);
    }
}


