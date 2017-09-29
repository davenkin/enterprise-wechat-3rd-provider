package davenkin.enterprise.wechat.suite;

import com.qq.weixin.mp.aes.AesException;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 9/29/17.
 */
@Component
public class MessageCryptor {

    private SuiteProperties suiteProperties;

    private ProviderProperties providerProperties;

    @Autowired
    public MessageCryptor(SuiteProperties suiteProperties, ProviderProperties providerProperties) {
        this.suiteProperties = suiteProperties;
        this.providerProperties = providerProperties;
    }

    private WXBizMsgCrypt suiteCrypt;

    public WXBizMsgCrypt suiteCrypt() {
        if (suiteCrypt != null) {
            return suiteCrypt;
        }
        try {
            suiteCrypt = new WXBizMsgCrypt(suiteProperties.getToken(), suiteProperties.getEncodingAesKey(), providerProperties.getCorpId());
            return suiteCrypt;
        } catch (AesException e) {
            throw new RuntimeException(e);
        }

    }

}
