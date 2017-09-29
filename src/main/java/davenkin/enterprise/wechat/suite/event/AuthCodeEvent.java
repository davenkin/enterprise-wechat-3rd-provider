package davenkin.enterprise.wechat.suite.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by yteng on 9/28/17.
 */
public class AuthCodeEvent extends BaseSuiteEvent {
    @XStreamAlias("AuthCode")
    private String authCode;

    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }
}
