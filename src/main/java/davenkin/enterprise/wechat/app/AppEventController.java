package davenkin.enterprise.wechat.app;

import com.qq.weixin.mp.aes.AesException;
import davenkin.enterprise.wechat.suite.MessageCryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yteng on 9/27/17.
 */
@RestController
@RequestMapping("/app")
public class AppEventController {
    private static Logger logger = LoggerFactory.getLogger(AppEventController.class);

    @Autowired
    private MessageCryptor messageCryptor;

    @GetMapping(value = "/events/{corpid}")
    public String appValidate(@PathVariable(value = "corpid") String corpId,
                              @RequestParam("echostr") String echoStr,
                              @RequestParam("msg_signature") String msgSignature,
                              @RequestParam("timestamp") String timestamp,
                              @RequestParam("nonce") String nonce) throws AesException {
        logger.info("Received validation request:[corpid:{}]-[echostr:{}]-[msg_signature:{}]-[timestamp:{}]-[nonce:{}]", corpId, echoStr, msgSignature, timestamp, nonce);

        return messageCryptor.suiteCrypt().VerifyURL(msgSignature, timestamp, nonce, echoStr);
    }

    @PostMapping(value = "/events/{corpid}")
    public String receiveEvents(@PathVariable(value = "corpid") String corpId,
                                @RequestParam("msg_signature") String msgSignature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestBody String eventBody) throws AesException {
        logger.info("Received event:{}", eventBody);
        String event = messageCryptor.suiteCrypt().DecryptMsg(msgSignature, timestamp, nonce, eventBody);
        logger.info("Decrypted event:{}", event);
        return "success";
    }

}


