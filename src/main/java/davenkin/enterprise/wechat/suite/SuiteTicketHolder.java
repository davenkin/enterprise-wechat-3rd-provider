package davenkin.enterprise.wechat.suite;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by yteng on 9/28/17.
 */

@Component
public class SuiteTicketHolder {
    private static Logger logger = LoggerFactory.getLogger(SuiteTicketHolder.class);
    private String ticket;

    public void updateTicket(String ticket) {
        logger.info("Updated suite ticket:{}", ticket);
        this.ticket = ticket;
    }

    public String ticket() {
        return this.ticket;
    }

    public boolean hasTicket() {
        return !StringUtils.isEmpty(ticket);
    }
}

