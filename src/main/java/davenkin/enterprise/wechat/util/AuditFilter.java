package davenkin.enterprise.wechat.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by yteng on 8/31/17.
 */

@Component
public class AuditFilter extends GenericFilterBean {
    private Logger logger = LoggerFactory.getLogger(AuditFilter.class);

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest res = (HttpServletRequest) request;
        String reqUrl = res.getRequestURL().toString();
        String queryString = res.getQueryString();
        if (queryString != null) {
            reqUrl += "?" + queryString;
        }

        logger.info("Request Arrived:{}", reqUrl);
        chain.doFilter(request, response);
    }
}
