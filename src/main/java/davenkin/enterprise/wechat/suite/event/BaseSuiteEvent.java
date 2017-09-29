package davenkin.enterprise.wechat.suite.event;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * Created by yteng on 9/28/17.
 */
public class BaseSuiteEvent {
    @XStreamAlias("SuiteId")
    private String suiteId;

    @XStreamAlias("InfoType")
    private String infoType;

    @XStreamAlias("TimeStamp")
    private Long timestamp;

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
}
