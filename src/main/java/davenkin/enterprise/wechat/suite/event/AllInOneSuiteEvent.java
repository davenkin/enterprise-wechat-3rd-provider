package davenkin.enterprise.wechat.suite.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by yteng on 9/28/17.
 */
public class AllInOneSuiteEvent {
    @XStreamAlias("SuiteId")
    private String suiteId;

    @XStreamAlias("InfoType")
    private String infoType;

    @XStreamAlias("TimeStamp")
    private Long timestamp;

    @XStreamAlias("AuthCode")
    private String authCode;

    @XStreamAlias("SuiteTicket")
    private String suiteTicket;

    public String getSuiteTicket() {
        return suiteTicket;
    }

    public void setSuiteTicket(String suiteTicket) {
        this.suiteTicket = suiteTicket;
    }


    public String getAuthCode() {
        return authCode;
    }

    public void setAuthCode(String authCode) {
        this.authCode = authCode;
    }


    public String getSuiteId() {
        return suiteId;
    }

    public void setSuiteId(String suiteId) {
        this.suiteId = suiteId;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public SuiteTicketEvent toSuiteTicketEvent() {
        SuiteTicketEvent suiteTicketEvent = new SuiteTicketEvent();
        suiteTicketEvent.setSuiteTicket(this.suiteTicket);
        suiteTicketEvent.setInfoType(this.infoType);
        suiteTicketEvent.setTimestamp(this.timestamp);
        suiteTicketEvent.setSuiteId(this.suiteId);
        return suiteTicketEvent;
    }
}
