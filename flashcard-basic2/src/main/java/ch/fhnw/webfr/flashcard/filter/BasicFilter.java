package ch.fhnw.webfr.flashcard.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
public class BasicFilter implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        log.debug("BasicFilter doFilter!");

        HttpServletRequest request = (HttpServletRequest) req;
        String servletPath = request.getPathInfo();

        log.info("Before request [uri={}]", servletPath);

        chain.doFilter(req, res);
    }

    @Override
    public void init(FilterConfig fConfig) {
        log.debug("BasicFilter init!");
    }

    @Override
    public void destroy() {
        log.debug("BasicFilter destroy!");
    }
}
