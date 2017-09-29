package davenkin.enterprise.wechat.suite;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yteng on 9/29/17.
 */
@RestController
public class PreAuthCodeController {

    private PreAuthCodeService preAuthCodeService;

    @Autowired
    public PreAuthCodeController(PreAuthCodeService preAuthCodeService) {
        this.preAuthCodeService = preAuthCodeService;
    }

    @GetMapping("/preAuthCode")
    public PreAuthCode getPreAuthCode() {
        return new PreAuthCode(preAuthCodeService.getPreAuthCode());
    }

}
