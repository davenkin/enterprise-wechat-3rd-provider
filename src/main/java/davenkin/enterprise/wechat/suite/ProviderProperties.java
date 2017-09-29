package davenkin.enterprise.wechat.suite;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 9/29/17.
 */
@Component
@ConfigurationProperties("provider")
public class ProviderProperties {
    private String corpId;
    private String providerSecret;

    public String getCorpId() {
        return corpId;
    }

    public void setCorpId(String corpId) {
        this.corpId = corpId;
    }

    public String getProviderSecret() {
        return providerSecret;
    }

    public void setProviderSecret(String providerSecret) {
        this.providerSecret = providerSecret;
    }
}
