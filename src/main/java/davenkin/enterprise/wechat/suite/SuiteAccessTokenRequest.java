package davenkin.enterprise.wechat.suite;

/**
 * Created by yteng on 9/29/17.
 */
public class SuiteAccessTokenRequest {
    private String suite_id;
    private String suite_secret;
    private String suite_ticket;

    public SuiteAccessTokenRequest(String suite_id, String suite_secret, String suite_ticket) {
        this.suite_id = suite_id;
        this.suite_secret = suite_secret;
        this.suite_ticket = suite_ticket;
    }
}
