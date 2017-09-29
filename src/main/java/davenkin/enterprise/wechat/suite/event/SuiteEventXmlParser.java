package davenkin.enterprise.wechat.suite.event;

import com.thoughtworks.xstream.XStream;
import org.springframework.stereotype.Component;

/**
 * Created by yteng on 9/28/17.
 */

@Component
public class SuiteEventXmlParser {
    private final XStream xStream = fromXmlXstream();

    private XStream fromXmlXstream() {
        XStream xStream = new XStream();
        xStream.autodetectAnnotations(true);
        xStream.ignoreUnknownElements();
        xStream.alias("xml", AllInOneSuiteEvent.class);
        return xStream;
    }

    public AllInOneSuiteEvent fromXml(String xml) {
        return (AllInOneSuiteEvent) xStream.fromXML(xml);
    }


}
