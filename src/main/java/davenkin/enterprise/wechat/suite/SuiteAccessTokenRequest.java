package davenkin.enterprise.wechat.suite;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by yteng on 9/29/17.
 */
public class SuiteAccessTokenRequest {
    @JsonProperty("suite_id")
    private String suiteId;

    @JsonProperty("suite_secret")
    private String suiteSecret;

    @JsonProperty("suite_ticket")
    private String suiteTicket;

    public SuiteAccessTokenRequest(String suiteId, String suiteSecret, String suiteTicket) {
        this.suiteId = suiteId;
        this.suiteSecret = suiteSecret;
        this.suiteTicket = suiteTicket;
    }
}
