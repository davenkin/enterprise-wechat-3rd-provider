package davenkin.enterprise.wechat.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.springframework.stereotype.Component;

import java.io.Writer;

/**
 * Created by yteng on 9/28/17.
 */

@Component
public class WechatXmlGenerator {
    private XStream toXmlxStream = getXstream();

    private XStream getXstream() {
        XStream xStream = new XStream(new XppDriver() {
            public HierarchicalStreamWriter createWriter(Writer out) {
                return new PrettyPrintWriter(out) {

                    protected void writeText(QuickWriter writer, String text) {
                        writer.write("<![CDATA[");
                        writer.write(text);
                        writer.write("]]>");
                    }
                };
            }
        });
        xStream.autodetectAnnotations(true);
        xStream.ignoreUnknownElements();

        return xStream;
    }

    public String toXml(Object object) {
        toXmlxStream.alias("xml", object.getClass());
        return toXmlxStream.toXML(object);
    }


}
