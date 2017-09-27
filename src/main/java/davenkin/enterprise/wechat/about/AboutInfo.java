package davenkin.enterprise.wechat.about;

/**
 * Created by yteng on 11/25/16.
 */
public class AboutInfo {
    private String buildNumber;
    private String buildTime;
    private String environment;
    private String deployTime;


    public AboutInfo(String buildNumber, String buildTime, String environment, String deployTime) {
        this.buildNumber = buildNumber;
        this.buildTime = buildTime;
        this.environment = environment;
        this.deployTime = deployTime;
    }

}
