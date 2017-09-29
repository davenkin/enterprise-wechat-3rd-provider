package davenkin.enterprise.wechat.suite;

import com.qq.weixin.mp.aes.AesException;
import davenkin.enterprise.wechat.suite.event.AllInOneSuiteEvent;
import davenkin.enterprise.wechat.suite.event.SuiteEventXmlParser;
import davenkin.enterprise.wechat.suite.event.SuiteTicketEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by yteng on 9/27/17.
 */
@RestController
@RequestMapping("/suite")
public class SuiteEventController {
    private static Logger logger = LoggerFactory.getLogger(SuiteEventController.class);

    @Autowired
    private SuiteEventXmlParser xmlParser;

    @Autowired
    private SuiteTicketHolder suiteTicketHolder;

    @Autowired
    private MessageCryptor messageCryptor;

    @GetMapping(value = "/events")
    public String validateSuite(@RequestParam("echostr") String echoStr,
                                @RequestParam("msg_signature") String msgSignature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce) throws AesException {
        logger.info("Received validation request:[echostr:{}]-[msg_signature:{}]-[timestamp:{}]-[nonce:{}]", echoStr, msgSignature, timestamp, nonce);
        return messageCryptor.suiteCrypt().VerifyURL(msgSignature, timestamp, nonce, echoStr);
    }


    @PostMapping(value = "/events")
    public String receiveEvents(@RequestParam("msg_signature") String msgSignature,
                                @RequestParam("timestamp") String timestamp,
                                @RequestParam("nonce") String nonce,
                                @RequestBody String eventBody) throws AesException {
        logger.info("Received event:{}", eventBody);
        String event = messageCryptor.suiteCrypt().DecryptMsg(msgSignature, timestamp, nonce, eventBody);
        logger.info("Decrypted event:{}", event);

        AllInOneSuiteEvent systemEvent = xmlParser.fromXml(event);
        String infoType = systemEvent.getInfoType();

        switch (infoType) {
            case "suite_ticket": {
                SuiteTicketEvent suiteTicketEvent = systemEvent.toSuiteTicketEvent();
                suiteTicketHolder.updateTicket(suiteTicketEvent.getSuiteTicket());
                break;
            }

            default: {

            }
        }

        return "success";
    }


}


