package davenkin.enterprise.wechat.callback;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by yteng on 9/27/17.
 */
@RestController
public class CallBackController {

    @Value("${token}")
    private String token;


    @Value("${encodingAesKey}")
    private String encodingAesKey;

    @Value("${corpId}")
    private String corpId;


    @GetMapping(value = "/callback")
    public String validate(@RequestParam("echostr") String echoStr,
                           @RequestParam("msg_signature") String msgSignature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce) throws AesException {
        return new WXBizMsgCrypt(token, encodingAesKey, corpId).VerifyURL(msgSignature, timestamp, nonce, echoStr);
    }
}

