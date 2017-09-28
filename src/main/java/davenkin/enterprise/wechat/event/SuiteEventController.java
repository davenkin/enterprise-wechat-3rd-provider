package davenkin.enterprise.wechat.event;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yteng on 9/27/17.
 */
@RestController
@RequestMapping("/suite")
public class SuiteEventController {
    private static Logger logger = LoggerFactory.getLogger(SuiteEventController.class);

    @Value("${token}")
    private String token;


    @Value("${encodingAesKey}")
    private String encodingAesKey;

    @Value("${corpId}")
    private String corpId;

    @GetMapping(value = "/events")
    public String validateSuite(@RequestParam("echostr") String echoStr,
                                @RequestParam("msg_signature") String msgSignature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce) throws AesException {
        logger.info("Received validation request:echostr:{}-msg_signature:{}-timestamp:{}-nonce:{}", echoStr, msgSignature, timestamp, nonce);
        return new WXBizMsgCrypt(token, encodingAesKey, corpId).VerifyURL(msgSignature, timestamp, nonce, echoStr);
    }


    @PostMapping(value = "/events")
    public String receiveEvents(@RequestParam("msg_signature") String msgSignature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestBody String eventBody) throws AesException {
        logger.info("Received event:{}", eventBody);
        String event = new WXBizMsgCrypt(token, encodingAesKey, corpId).DecryptMsg(msgSignature, timestamp, nonce, eventBody);
        logger.info("Decrypted event:{}", event);
        return "success";
    }

}


