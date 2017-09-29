package davenkin.enterprise.wechat.suite;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yteng on 9/29/17.
 */
public class SuiteAccessTokenResponse {
    @JsonProperty("expires_in")
    private String expiresIn;

    @JsonProperty("suite_access_token")
    private String suiteAccessToken;

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public void setSuiteAccessToken(String suiteAccessToken) {
        this.suiteAccessToken = suiteAccessToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public String getSuiteAccessToken() {
        return suiteAccessToken;
    }
}
