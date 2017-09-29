package davenkin.enterprise.wechat.suite.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by yteng on 9/28/17.
 */
public class SuiteTicketEvent extends BaseSuiteEvent {
    @XStreamAlias("SuiteTicket")
    private String suiteTicket;

    public String getSuiteTicket() {
        return suiteTicket;
    }

    public void setSuiteTicket(String suiteTicket) {
        this.suiteTicket = suiteTicket;
    }
}
